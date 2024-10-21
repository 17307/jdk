/*
 * Copyright Amazon.com Inc. or its affiliates. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

#include "precompiled.hpp"
#include "gc/shenandoah/shenandoahControlThread.hpp"
#include "gc/shenandoah/shenandoahHeap.inline.hpp"
#include "gc/shenandoah/shenandoahHeapRegion.hpp"
#include "gc/shenandoah/shenandoahUncommitThread.hpp"
#include "logging/log.hpp"
#include "runtime/mutexLocker.hpp"
#include "utilities/events.hpp"

ShenandoahUncommitThread::ShenandoahUncommitThread(ShenandoahHeap* heap)
  : _heap(heap), _lock(Mutex::safepoint - 2, "ShenandoahUncommit_lock, true") {
  set_name("Shenandoah Uncommit Thread");
  create_and_start();
}

void ShenandoahUncommitThread::run_service() {
  assert(ShenandoahUncommit, "Thread should only run when uncommit is enabled");

  // Shrink period avoids constantly polling regions for shrinking.
  // Having a period 10x lower than the delay would mean we hit the
  // shrinking with lag of less than 1/10-th of true delay.
  // ShenandoahUncommitDelay is in millis, but shrink_period is in seconds.
  double shrink_period = (double)ShenandoahUncommitDelay / 1000 / 10;
  double last_shrink_time = os::elapsedTime();
  while (!should_terminate()) {
    double current = os::elapsedTime();
    bool soft_max_changed = _soft_max_changed.try_unset();
    bool explicit_gc_requested = _explicit_gc_requested.try_unset();

    if (soft_max_changed || explicit_gc_requested || current - last_shrink_time > shrink_period) {
      double shrink_before = (soft_max_changed || explicit_gc_requested) ? current : current - ((double) ShenandoahUncommitDelay / 1000.0);
      size_t shrink_until = soft_max_changed ? _heap->soft_max_capacity() : _heap->min_capacity();

      // Explicit GC tries to uncommit everything down to min capacity.
      // Soft max change tries to uncommit everything down to target capacity.
      // Periodic uncommit tries to uncommit suitable regions down to min capacity.
      if (has_work(shrink_before, shrink_until)) {
        uncommit(shrink_before, shrink_until);
        last_shrink_time = current;
      }
    }
    MonitorLocker locker(&_lock);
    locker.wait((int64_t )shrink_period);
  }
}

bool ShenandoahUncommitThread::has_work(double shrink_before, size_t shrink_until) const {
  // Determine if there is work to do. This avoids taking heap lock if there is
  // no work available, avoids spamming logs with superfluous logging messages,
  // and minimises the amount of work while locks are taken.

  if (_heap->committed() <= shrink_until) {
    return false;
  }

  for (size_t i = 0; i < _heap->num_regions(); i++) {
    ShenandoahHeapRegion *r = _heap->get_region(i);
    if (r->is_empty_committed() && (r->empty_time() < shrink_before)) {
      return true;
    }
  }

  return false;
}

void ShenandoahUncommitThread::notify_soft_max_changed() {
  if (_soft_max_changed.try_set()) {
    MonitorLocker locker(&_lock);
    locker.notify_all();
  }
}

void ShenandoahUncommitThread::notify_explicit_gc_requested() {
  if (_explicit_gc_requested.try_set()) {
    MonitorLocker locker(&_lock);
    locker.notify_all();
  }
}

void ShenandoahUncommitThread::uncommit(double shrink_before, size_t shrink_until) {
  assert (ShenandoahUncommit, "should be enabled");

  EventMark em("Concurrent uncommit");
  log_info(gc)("Uncommit regions empty before: %.3f, until committed is less than: " PROPERFMT,
          shrink_before, PROPERFMTARGS(shrink_until + ShenandoahHeapRegion::region_size_bytes()));

  // Application allocates from the beginning of the heap, and GC allocates at
  // the end of it. It is more efficient to uncommit from the end, so that applications
  // could enjoy the near committed regions. GC allocations are much less frequent,
  // and therefore can accept the committing costs.
  size_t count = 0;
  for (size_t i = _heap->num_regions(); i > 0; i--) { // care about size_t underflow
    ShenandoahHeapRegion* r = _heap->get_region(i - 1);
    if (r->is_empty_committed() && (r->empty_time() < shrink_before)) {
      ShenandoahHeapLocker locker(_heap->lock());
      if (r->is_empty_committed()) {
        if (_heap->committed() < shrink_until + ShenandoahHeapRegion::region_size_bytes()) {
          break;
        }

        r->make_uncommitted();
        count++;
      }
    }
    SpinPause(); // allow allocators to take the lock
  }

  if (count > 0) {
    log_info(gc)("Uncommitted " SIZE_FORMAT " regions", count);
    _heap->notify_heap_changed();
  }
}
