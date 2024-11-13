/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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
 */
package compiler.c2.irTests;

import jdk.test.lib.Asserts;
import compiler.lib.ir_framework.*;

/*
 * @test
 * @bug 8332268
 * @summary Test that Ideal transformations of ModINode* are being performed as expected.
 * @library /test/lib /
 * @run driver compiler.c2.irTests.ModINodeIdealizationTests
 */
public class ModINodeIdealizationTests {
    public static void main(String[] args) {
        TestFramework.run();
    }

    @Run(test = {"constant", "constantAgain", "powerOf2", "powerOf2Minus1"})
    public void runMethod() {
        int a = RunInfo.getRandom().nextInt();
        a = (a == 0) ? 2 : a;
        int b = RunInfo.getRandom().nextInt();
        b = (b == 0) ? 2 : b;

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        assertResult(0, 0, true);
        assertResult(a, b, false);
        assertResult(min, min, false);
        assertResult(max, max, false);
    }

    @DontCompile
    public void assertResult(int a, int b, boolean shouldThrow) {
        try {
            Asserts.assertEQ(a % a, constant(a));
            Asserts.assertFalse(shouldThrow, "Expected an exception to be thrown.");
        } catch (ArithmeticException e) {
            Asserts.assertTrue(shouldThrow, "Did not expected an exception to be thrown.");
        }

        Asserts.assertEQ(Math.abs(a) % 32, powerOf2(a));
        Asserts.assertEQ(a % 127, powerOf2Minus1(a));
        Asserts.assertEQ(a % 1, constantAgain(a));
    }

    @Test
    @IR(failOn = {IRNode.MOD_I, IRNode.MUL})
    @IR(counts = {IRNode.DIV_BY_ZERO_TRAP, "1"})
    // Checks x % x => 0
    public int constant(int x) {
        return x % x;
    }

    @Test
    @IR(failOn = {IRNode.MOD_I})
    // Checks x % 1 => 0
    public int constantAgain(int x) {
        return x % 1;
    }

    @Test
    @IR(failOn = {IRNode.MOD_I, IRNode.RSHIFT, IRNode.ADD})
    @IR(counts = {IRNode.AND_I, "1"})
    // If the dividend is positive, and divisor is of the form 2^k, we can use a simple bit mask.
    public int powerOf2(int x) {
        return Math.abs(x) % 32;
    }

    @Test
    @IR(failOn = {IRNode.MOD_I})
    @IR(counts = {IRNode.AND_I, ">=1", IRNode.RSHIFT, ">=1", IRNode.CMP_I, "2"})
    // Special optimization for the case 2^k-1 for bigger k
    public int powerOf2Minus1(int x) {
        return x % 127;
    }
}
