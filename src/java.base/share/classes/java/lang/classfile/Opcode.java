/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
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
package java.lang.classfile;

import java.lang.classfile.instruction.*;

import jdk.internal.classfile.impl.RawBytecodeHelper;

/**
 * Describes the opcodes of the JVM instruction set, as described in JVMS {@jvms 6.5}.
 * This includes a few pseudo-opcodes modified by {@link #isWide() wide}.
 *
 * @apiNote
 * The enum constants are named after the opcodes' mnemonics in uppercase.
 * Wide pseudo-opcodes are named with the original opcodes' mnemonic plus
 * a {@code _W} suffix. However, {@link #LDC_W ldc_w}, {@link #LDC2_W ldc2_w},
 * {@link #GOTO_W goto_w}, and {@link #JSR_W jsr_w} are legitimate opcodes
 * instead of wide pseudo-opcodes.
 *
 * @see Instruction
 *
 * @since 24
 */
public enum Opcode {

    /**
     * Do nothing.
     *
     * @jvms 6.5.nop <em>nop</em>
     */
    NOP(RawBytecodeHelper.NOP, 1, Kind.NOP),

    /**
     * Push {@code null}.
     *
     * @jvms 6.5.aconst_null <em>aconst_null</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ACONST_NULL(RawBytecodeHelper.ACONST_NULL, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code -1}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_M1(RawBytecodeHelper.ICONST_M1, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 0}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_0(RawBytecodeHelper.ICONST_0, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 1}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_1(RawBytecodeHelper.ICONST_1, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 2}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_2(RawBytecodeHelper.ICONST_2, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 3}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_3(RawBytecodeHelper.ICONST_3, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 4}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_4(RawBytecodeHelper.ICONST_4, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} constant {@code 5}.
     *
     * @jvms 6.5.iconst_i <em>iconst_&lt;i&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    ICONST_5(RawBytecodeHelper.ICONST_5, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#LONG long} constant {@code 0}.
     *
     * @jvms 6.5.lconst_l <em>lconst_&lt;l&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    LCONST_0(RawBytecodeHelper.LCONST_0, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#LONG long} constant {@code 1}.
     *
     * @jvms 6.5.lconst_l <em>lconst_&lt;l&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    LCONST_1(RawBytecodeHelper.LCONST_1, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#FLOAT float} constant {@code 0}.
     *
     * @jvms 6.5.fconst_f <em>fconst_&lt;f&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    FCONST_0(RawBytecodeHelper.FCONST_0, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#FLOAT float} constant {@code 1}.
     *
     * @jvms 6.5.fconst_f <em>fconst_&lt;f&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    FCONST_1(RawBytecodeHelper.FCONST_1, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#FLOAT float} constant {@code 2}.
     *
     * @jvms 6.5.fconst_f <em>fconst_&lt;f&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    FCONST_2(RawBytecodeHelper.FCONST_2, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#DOUBLE double} constant {@code 0}.
     *
     * @jvms 6.5.dconst_d <em>dconst_&lt;d&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    DCONST_0(RawBytecodeHelper.DCONST_0, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#DOUBLE double} constant {@code 1}.
     *
     * @jvms 6.5.dconst_d <em>dconst_&lt;d&gt;</em>
     * @see ConstantInstruction.IntrinsicConstantInstruction
     */
    DCONST_1(RawBytecodeHelper.DCONST_1, 1, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} value from sign-extension of immediate
     * {@link TypeKind#BYTE byte} value.
     *
     * @jvms 6.5.bipush <em>bipush</em>
     * @see ConstantInstruction.ArgumentConstantInstruction
     */
    BIPUSH(RawBytecodeHelper.BIPUSH, 2, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#INT int} value from sign-extension of immediate
     * {@link TypeKind#SHORT short} value.
     *
     * @jvms 6.5.sipush <em>sipush</em>
     * @see ConstantInstruction.ArgumentConstantInstruction
     */
    SIPUSH(RawBytecodeHelper.SIPUSH, 3, Kind.CONSTANT),

    /**
     * Push item from run-time constant pool.
     *
     * @jvms 6.5.ldc <em>ldc</em>
     * @see ConstantInstruction.LoadConstantInstruction
     */
    LDC(RawBytecodeHelper.LDC, 2, Kind.CONSTANT),

    /**
     * Push item from run-time constant pool (wide index).
     *
     * @jvms 6.5.ldc_w <em>ldc_w</em>
     * @see ConstantInstruction.LoadConstantInstruction
     */
    LDC_W(RawBytecodeHelper.LDC_W, 3, Kind.CONSTANT),

    /**
     * Push {@link TypeKind#LONG long} or {@link TypeKind#DOUBLE double}
     * from run-time constant pool (wide index).
     *
     * @jvms 6.5.ldc2_w <em>ldc2_w</em>
     * @see ConstantInstruction.LoadConstantInstruction
     */
    LDC2_W(RawBytecodeHelper.LDC2_W, 3, Kind.CONSTANT),

    /**
     * Load {@link TypeKind#INT int} from local variable.
     *
     * @jvms 6.5.iload <em>iload</em>
     */
    ILOAD(RawBytecodeHelper.ILOAD, 2, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable.
     *
     * @jvms 6.5.lload <em>lload</em>
     */
    LLOAD(RawBytecodeHelper.LLOAD, 2, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable.
     *
     * @jvms 6.5.fload <em>fload</em>
     */
    FLOAD(RawBytecodeHelper.FLOAD, 2, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable.
     *
     * @jvms 6.5.dload <em>dload</em>
     */
    DLOAD(RawBytecodeHelper.DLOAD, 2, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable.
     *
     * @jvms 6.5.aload <em>aload</em>
     */
    ALOAD(RawBytecodeHelper.ALOAD, 2, Kind.LOAD),

    /**
     * Load {@link TypeKind#INT int} from local variable slot {@code 0}.
     *
     * @jvms 6.5.iload_n <em>iload_&lt;n&gt;</em>
     */
    ILOAD_0(RawBytecodeHelper.ILOAD_0, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#INT int} from local variable slot {@code 1}.
     *
     * @jvms 6.5.iload_n <em>iload_&lt;n&gt;</em>
     */
    ILOAD_1(RawBytecodeHelper.ILOAD_1, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#INT int} from local variable slot {@code 2}.
     *
     * @jvms 6.5.iload_n <em>iload_&lt;n&gt;</em>
     */
    ILOAD_2(RawBytecodeHelper.ILOAD_2, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#INT int} from local variable slot {@code 3}.
     *
     * @jvms 6.5.iload_n <em>iload_&lt;n&gt;</em>
     */
    ILOAD_3(RawBytecodeHelper.ILOAD_3, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable slot {@code 0}.
     *
     * @jvms 6.5.lload_n <em>lload_&lt;n&gt;</em>
     */
    LLOAD_0(RawBytecodeHelper.LLOAD_0, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable slot {@code 1}.
     *
     * @jvms 6.5.lload_n <em>lload_&lt;n&gt;</em>
     */
    LLOAD_1(RawBytecodeHelper.LLOAD_1, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable slot {@code 2}.
     *
     * @jvms 6.5.lload_n <em>lload_&lt;n&gt;</em>
     */
    LLOAD_2(RawBytecodeHelper.LLOAD_2, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable slot {@code 3}.
     *
     * @jvms 6.5.lload_n <em>lload_&lt;n&gt;</em>
     */
    LLOAD_3(RawBytecodeHelper.LLOAD_3, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable slot {@code 0}.
     *
     * @jvms 6.5.fload_n <em>fload_&lt;n&gt;</em>
     */
    FLOAD_0(RawBytecodeHelper.FLOAD_0, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable slot {@code 1}.
     *
     * @jvms 6.5.fload_n <em>fload_&lt;n&gt;</em>
     */
    FLOAD_1(RawBytecodeHelper.FLOAD_1, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable slot {@code 2}.
     *
     * @jvms 6.5.fload_n <em>fload_&lt;n&gt;</em>
     */
    FLOAD_2(RawBytecodeHelper.FLOAD_2, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable slot {@code 3}.
     *
     * @jvms 6.5.fload_n <em>fload_&lt;n&gt;</em>
     */
    FLOAD_3(RawBytecodeHelper.FLOAD_3, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable slot {@code 0}.
     *
     * @jvms 6.5.dload_n <em>dload_&lt;n&gt;</em>
     */
    DLOAD_0(RawBytecodeHelper.DLOAD_0, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable slot {@code 1}.
     *
     * @jvms 6.5.dload_n <em>dload_&lt;n&gt;</em>
     */
    DLOAD_1(RawBytecodeHelper.DLOAD_1, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable slot {@code 2}.
     *
     * @jvms 6.5.dload_n <em>dload_&lt;n&gt;</em>
     */
    DLOAD_2(RawBytecodeHelper.DLOAD_2, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable slot {@code 3}.
     *
     * @jvms 6.5.dload_n <em>dload_&lt;n&gt;</em>
     */
    DLOAD_3(RawBytecodeHelper.DLOAD_3, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable slot {@code 0}.
     *
     * @jvms 6.5.aload_n <em>aload_&lt;n&gt;</em>
     */
    ALOAD_0(RawBytecodeHelper.ALOAD_0, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable slot {@code 1}.
     *
     * @jvms 6.5.aload_n <em>aload_&lt;n&gt;</em>
     */
    ALOAD_1(RawBytecodeHelper.ALOAD_1, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable slot {@code 2}.
     *
     * @jvms 6.5.aload_n <em>aload_&lt;n&gt;</em>
     */
    ALOAD_2(RawBytecodeHelper.ALOAD_2, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable slot {@code 3}.
     *
     * @jvms 6.5.aload_n <em>aload_&lt;n&gt;</em>
     */
    ALOAD_3(RawBytecodeHelper.ALOAD_3, 1, Kind.LOAD),

    /**
     * Load {@link TypeKind#INT int} from array.
     *
     * @jvms 6.5.iaload <em>iaload</em>
     */
    IALOAD(RawBytecodeHelper.IALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#LONG long} from array.
     *
     * @jvms 6.5.laload <em>laload</em>
     */
    LALOAD(RawBytecodeHelper.LALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from array.
     *
     * @jvms 6.5.faload <em>faload</em>
     */
    FALOAD(RawBytecodeHelper.FALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from array.
     *
     * @jvms 6.5.daload <em>daload</em>
     */
    DALOAD(RawBytecodeHelper.DALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from array.
     *
     * @jvms 6.5.aaload <em>aaload</em>
     */
    AALOAD(RawBytecodeHelper.AALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#BYTE byte} or {@link TypeKind#BOOLEAN boolean} from array.
     *
     * @jvms 6.5.baload <em>baload</em>
     */
    BALOAD(RawBytecodeHelper.BALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#CHAR char} from array.
     *
     * @jvms 6.5.caload <em>caload</em>
     */
    CALOAD(RawBytecodeHelper.CALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Load {@link TypeKind#SHORT short} from array.
     *
     * @jvms 6.5.saload <em>saload</em>
     */
    SALOAD(RawBytecodeHelper.SALOAD, 1, Kind.ARRAY_LOAD),

    /**
     * Store {@link TypeKind#INT int} into local variable.
     *
     * @jvms 6.5.istore <em>istore</em>
     */
    ISTORE(RawBytecodeHelper.ISTORE, 2, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable.
     *
     * @jvms 6.5.lstore <em>lstore</em>
     */
    LSTORE(RawBytecodeHelper.LSTORE, 2, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable.
     *
     * @jvms 6.5.fstore <em>fstore</em>
     */
    FSTORE(RawBytecodeHelper.FSTORE, 2, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable.
     *
     * @jvms 6.5.dstore <em>dstore</em>
     */
    DSTORE(RawBytecodeHelper.DSTORE, 2, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.astore <em>astore</em>
     */
    ASTORE(RawBytecodeHelper.ASTORE, 2, Kind.STORE),

    /**
     * Store {@link TypeKind#INT int} into local variable slot {@code 0}.
     *
     * @jvms 6.5.istore_n <em>istore_&lt;n&gt;</em>
     */
    ISTORE_0(RawBytecodeHelper.ISTORE_0, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#INT int} into local variable slot {@code 1}.
     *
     * @jvms 6.5.istore_n <em>istore_&lt;n&gt;</em>
     */
    ISTORE_1(RawBytecodeHelper.ISTORE_1, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#INT int} into local variable slot {@code 2}.
     *
     * @jvms 6.5.istore_n <em>istore_&lt;n&gt;</em>
     */
    ISTORE_2(RawBytecodeHelper.ISTORE_2, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#INT int} into local variable slot {@code 3}.
     *
     * @jvms 6.5.istore_n <em>istore_&lt;n&gt;</em>
     */
    ISTORE_3(RawBytecodeHelper.ISTORE_3, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable slot {@code 0}.
     *
     * @jvms 6.5.lstore_n <em>lstore_&lt;n&gt;</em>
     */
    LSTORE_0(RawBytecodeHelper.LSTORE_0, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable slot {@code 1}.
     *
     * @jvms 6.5.lstore_n <em>lstore_&lt;n&gt;</em>
     */
    LSTORE_1(RawBytecodeHelper.LSTORE_1, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable slot {@code 2}.
     *
     * @jvms 6.5.lstore_n <em>lstore_&lt;n&gt;</em>
     */
    LSTORE_2(RawBytecodeHelper.LSTORE_2, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable slot {@code 3}.
     *
     * @jvms 6.5.lstore_n <em>lstore_&lt;n&gt;</em>
     */
    LSTORE_3(RawBytecodeHelper.LSTORE_3, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable slot {@code 0}.
     *
     * @jvms 6.5.fstore_n <em>fstore_&lt;n&gt;</em>
     */
    FSTORE_0(RawBytecodeHelper.FSTORE_0, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable slot {@code 1}.
     *
     * @jvms 6.5.fstore_n <em>fstore_&lt;n&gt;</em>
     */
    FSTORE_1(RawBytecodeHelper.FSTORE_1, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable slot {@code 2}.
     *
     * @jvms 6.5.fstore_n <em>fstore_&lt;n&gt;</em>
     */
    FSTORE_2(RawBytecodeHelper.FSTORE_2, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable slot {@code 3}.
     *
     * @jvms 6.5.fstore_n <em>fstore_&lt;n&gt;</em>
     */
    FSTORE_3(RawBytecodeHelper.FSTORE_3, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable slot {@code 0}.
     *
     * @jvms 6.5.dstore_n <em>dstore_&lt;n&gt;</em>
     */
    DSTORE_0(RawBytecodeHelper.DSTORE_0, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable slot {@code 1}.
     *
     * @jvms 6.5.dstore_n <em>dstore_&lt;n&gt;</em>
     */
    DSTORE_1(RawBytecodeHelper.DSTORE_1, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable slot {@code 2}.
     *
     * @jvms 6.5.dstore_n <em>dstore_&lt;n&gt;</em>
     */
    DSTORE_2(RawBytecodeHelper.DSTORE_2, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable slot {@code 3}.
     *
     * @jvms 6.5.dstore_n <em>dstore_&lt;n&gt;</em>
     */
    DSTORE_3(RawBytecodeHelper.DSTORE_3, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable slot {@code 0}.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.astore_n <em>astore_&lt;n&gt;</em>
     */
    ASTORE_0(RawBytecodeHelper.ASTORE_0, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable slot {@code 1}.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.astore_n <em>astore_&lt;n&gt;</em>
     */
    ASTORE_1(RawBytecodeHelper.ASTORE_1, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable slot {@code 2}.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.astore_n <em>astore_&lt;n&gt;</em>
     */
    ASTORE_2(RawBytecodeHelper.ASTORE_2, 1, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable slot {@code 3}.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.astore_n <em>astore_&lt;n&gt;</em>
     */
    ASTORE_3(RawBytecodeHelper.ASTORE_3, 1, Kind.STORE),

    /**
     * Store into {@link TypeKind#INT int} array.
     *
     * @jvms 6.5.iastore <em>iastore</em>
     */
    IASTORE(RawBytecodeHelper.IASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#LONG long} array.
     *
     * @jvms 6.5.lastore <em>lastore</em>
     */
    LASTORE(RawBytecodeHelper.LASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#FLOAT float} array.
     *
     * @jvms 6.5.fastore <em>fastore</em>
     */
    FASTORE(RawBytecodeHelper.FASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#DOUBLE double} array.
     *
     * @jvms 6.5.dastore <em>dastore</em>
     */
    DASTORE(RawBytecodeHelper.DASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#REFERENCE reference} array.
     *
     * @jvms 6.5.aastore <em>aastore</em>
     */
    AASTORE(RawBytecodeHelper.AASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#BYTE byte} or {@link TypeKind#BOOLEAN boolean} array.
     *
     * @jvms 6.5.bastore <em>bastore</em>
     */
    BASTORE(RawBytecodeHelper.BASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#CHAR char} array.
     *
     * @jvms 6.5.castore <em>castore</em>
     */
    CASTORE(RawBytecodeHelper.CASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Store into {@link TypeKind#SHORT short} array.
     *
     * @jvms 6.5.sastore <em>sastore</em>
     */
    SASTORE(RawBytecodeHelper.SASTORE, 1, Kind.ARRAY_STORE),

    /**
     * Pop the top operand stack value.
     *
     * @jvms 6.5.pop <em>pop</em>
     */
    POP(RawBytecodeHelper.POP, 1, Kind.STACK),

    /**
     * Pop the top one or two operand stack values.
     *
     * @jvms 6.5.pop2 <em>pop2</em>
     */
    POP2(RawBytecodeHelper.POP2, 1, Kind.STACK),

    /**
     * Duplicate the top operand stack value.
     *
     * @jvms 6.5.dup <em>dup</em>
     */
    DUP(RawBytecodeHelper.DUP, 1, Kind.STACK),

    /**
     * Duplicate the top operand stack value and insert two values down.
     *
     * @jvms 6.5.dup_x1 <em>dup_x1</em>
     */
    DUP_X1(RawBytecodeHelper.DUP_X1, 1, Kind.STACK),

    /**
     * Duplicate the top operand stack value and insert two or three values down.
     *
     * @jvms 6.5.dup_x2 <em>dup_x2</em>
     */
    DUP_X2(RawBytecodeHelper.DUP_X2, 1, Kind.STACK),

    /**
     * Duplicate the top one or two operand stack values.
     *
     * @jvms 6.5.dup2 <em>dup2</em>
     */
    DUP2(RawBytecodeHelper.DUP2, 1, Kind.STACK),

    /**
     * Duplicate the top one or two operand stack values and insert two or three
     * values down.
     *
     * @jvms 6.5.dup2_x1 <em>dup2_x1</em>
     */
    DUP2_X1(RawBytecodeHelper.DUP2_X1, 1, Kind.STACK),

    /**
     * Duplicate the top one or two operand stack values and insert two, three,
     * or four values down.
     *
     * @jvms 6.5.dup2_x2 <em>dup2_x2</em>
     */
    DUP2_X2(RawBytecodeHelper.DUP2_X2, 1, Kind.STACK),

    /**
     * Swap the top two operand stack values.
     *
     * @jvms 6.5.swap <em>swap</em>
     */
    SWAP(RawBytecodeHelper.SWAP, 1, Kind.STACK),

    /**
     * Add {@link TypeKind#INT int}.
     *
     * @jvms 6.5.iadd <em>iadd</em>
     */
    IADD(RawBytecodeHelper.IADD, 1, Kind.OPERATOR),

    /**
     * Add {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.ladd <em>ladd</em>
     */
    LADD(RawBytecodeHelper.LADD, 1, Kind.OPERATOR),

    /**
     * Add {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.fadd <em>fadd</em>
     */
    FADD(RawBytecodeHelper.FADD, 1, Kind.OPERATOR),

    /**
     * Add {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.dadd <em>dadd</em>
     */
    DADD(RawBytecodeHelper.DADD, 1, Kind.OPERATOR),

    /**
     * Subtract {@link TypeKind#INT int}.
     *
     * @jvms 6.5.isub <em>isub</em>
     */
    ISUB(RawBytecodeHelper.ISUB, 1, Kind.OPERATOR),

    /**
     * Subtract {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lsub <em>lsub</em>
     */
    LSUB(RawBytecodeHelper.LSUB, 1, Kind.OPERATOR),

    /**
     * Subtract {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.fsub <em>fsub</em>
     */
    FSUB(RawBytecodeHelper.FSUB, 1, Kind.OPERATOR),

    /**
     * Subtract {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.dsub <em>dsub</em>
     */
    DSUB(RawBytecodeHelper.DSUB, 1, Kind.OPERATOR),

    /**
     * Multiply {@link TypeKind#INT int}.
     *
     * @jvms 6.5.imul <em>imul</em>
     */
    IMUL(RawBytecodeHelper.IMUL, 1, Kind.OPERATOR),

    /**
     * Multiply {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lmul <em>lmul</em>
     */
    LMUL(RawBytecodeHelper.LMUL, 1, Kind.OPERATOR),

    /**
     * Multiply {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.fmul <em>fmul</em>
     */
    FMUL(RawBytecodeHelper.FMUL, 1, Kind.OPERATOR),

    /**
     * Multiply {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.dmul <em>dmul</em>
     */
    DMUL(RawBytecodeHelper.DMUL, 1, Kind.OPERATOR),

    /**
     * Divide {@link TypeKind#INT int}.
     *
     * @jvms 6.5.idiv <em>idiv</em>
     */
    IDIV(RawBytecodeHelper.IDIV, 1, Kind.OPERATOR),

    /**
     * Divide {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.ldiv <em>ldiv</em>
     */
    LDIV(RawBytecodeHelper.LDIV, 1, Kind.OPERATOR),

    /**
     * Divide {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.fdiv <em>fdiv</em>
     */
    FDIV(RawBytecodeHelper.FDIV, 1, Kind.OPERATOR),

    /**
     * Divide {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.ddiv <em>ddiv</em>
     */
    DDIV(RawBytecodeHelper.DDIV, 1, Kind.OPERATOR),

    /**
     * Remainder {@link TypeKind#INT int}.
     *
     * @jvms 6.5.irem <em>irem</em>
     */
    IREM(RawBytecodeHelper.IREM, 1, Kind.OPERATOR),

    /**
     * Remainder {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lrem <em>lrem</em>
     */
    LREM(RawBytecodeHelper.LREM, 1, Kind.OPERATOR),

    /**
     * Remainder {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.frem <em>frem</em>
     */
    FREM(RawBytecodeHelper.FREM, 1, Kind.OPERATOR),

    /**
     * Remainder {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.drem <em>drem</em>
     */
    DREM(RawBytecodeHelper.DREM, 1, Kind.OPERATOR),

    /**
     * Negate {@link TypeKind#INT int}.
     *
     * @jvms 6.5.ineg <em>ineg</em>
     */
    INEG(RawBytecodeHelper.INEG, 1, Kind.OPERATOR),

    /**
     * Negate {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lneg <em>lneg</em>
     */
    LNEG(RawBytecodeHelper.LNEG, 1, Kind.OPERATOR),

    /**
     * Negate {@link TypeKind#FLOAT float}.
     *
     * @jvms 6.5.fneg <em>fneg</em>
     */
    FNEG(RawBytecodeHelper.FNEG, 1, Kind.OPERATOR),

    /**
     * Negate {@link TypeKind#DOUBLE double}.
     *
     * @jvms 6.5.dneg <em>dneg</em>
     */
    DNEG(RawBytecodeHelper.DNEG, 1, Kind.OPERATOR),

    /**
     * Shift left {@link TypeKind#INT int}.
     *
     * @jvms 6.5.ishl <em>ishl</em>
     */
    ISHL(RawBytecodeHelper.ISHL, 1, Kind.OPERATOR),

    /**
     * Shift left {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lshl <em>lshl</em>
     */
    LSHL(RawBytecodeHelper.LSHL, 1, Kind.OPERATOR),

    /**
     * Arithmetic shift right {@link TypeKind#INT int}.
     *
     * @jvms 6.5.ishr <em>ishr</em>
     */
    ISHR(RawBytecodeHelper.ISHR, 1, Kind.OPERATOR),

    /**
     * Arithmetic shift right {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lshr <em>lshr</em>
     */
    LSHR(RawBytecodeHelper.LSHR, 1, Kind.OPERATOR),

    /**
     * Logical shift right {@link TypeKind#INT int}.
     *
     * @jvms 6.5.iushr <em>iushr</em>
     */
    IUSHR(RawBytecodeHelper.IUSHR, 1, Kind.OPERATOR),

    /**
     * Logical shift right {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lushr <em>lushr</em>
     */
    LUSHR(RawBytecodeHelper.LUSHR, 1, Kind.OPERATOR),

    /**
     * Bitwise AND {@link TypeKind#INT int}.
     *
     * @apiNote
     * This may be used to implement {@link TypeKind#BOOLEAN boolean} AND.
     *
     * @jvms 6.5.iand <em>iand</em>
     */
    IAND(RawBytecodeHelper.IAND, 1, Kind.OPERATOR),

    /**
     * Bitwise AND {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.land <em>land</em>
     */
    LAND(RawBytecodeHelper.LAND, 1, Kind.OPERATOR),

    /**
     * Bitwise OR {@link TypeKind#INT int}.
     *
     * @apiNote
     * This may be used to implement {@link TypeKind#BOOLEAN boolean} OR.
     *
     * @jvms 6.5.ior <em>ior</em>
     */
    IOR(RawBytecodeHelper.IOR, 1, Kind.OPERATOR),

    /**
     * Bitwise OR {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lor <em>lor</em>
     */
    LOR(RawBytecodeHelper.LOR, 1, Kind.OPERATOR),

    /**
     * Bitwise XOR {@link TypeKind#INT int}.
     *
     * @apiNote
     * This may be used to implement {@link TypeKind#BOOLEAN boolean} XOR.
     *
     * @jvms 6.5.ixor <em>ixor</em>
     */
    IXOR(RawBytecodeHelper.IXOR, 1, Kind.OPERATOR),

    /**
     * Bitwise XOR {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.lxor <em>lxor</em>
     */
    LXOR(RawBytecodeHelper.LXOR, 1, Kind.OPERATOR),

    /**
     * Increment local variable by constant.
     *
     * @jvms 6.5.iinc <em>iinc</em>
     */
    IINC(RawBytecodeHelper.IINC, 3, Kind.INCREMENT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#LONG long}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.i2l <em>i2l</em>
     */
    I2L(RawBytecodeHelper.I2L, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#FLOAT float}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.i2f <em>i2f</em>
     */
    I2F(RawBytecodeHelper.I2F, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#DOUBLE double}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.i2d <em>i2d</em>
     */
    I2D(RawBytecodeHelper.I2D, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#LONG long} to {@link TypeKind#INT int}.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.l2i <em>l2i</em>
     */
    L2I(RawBytecodeHelper.L2I, 1, Kind.CONVERT),

    /** Convert {@link TypeKind#LONG long} to {@link TypeKind#FLOAT float}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.l2f <em>l2f</em>
     */
    L2F(RawBytecodeHelper.L2F, 1, Kind.CONVERT),

    /** Convert {@link TypeKind#LONG long} to {@link TypeKind#DOUBLE double}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.l2d <em>l2d</em>
     */
    L2D(RawBytecodeHelper.L2D, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#FLOAT float} to {@link TypeKind#INT int}.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.f2i <em>f2i</em>
     */
    F2I(RawBytecodeHelper.F2I, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#FLOAT float} to {@link TypeKind#LONG long}.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.f2l <em>f2l</em>
     */
    F2L(RawBytecodeHelper.F2L, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#FLOAT float} to {@link TypeKind#DOUBLE double}.
     *
     * @jls 5.1.2 Widening Primitive Conversion
     * @jvms 6.5.f2d <em>f2d</em>
     */
    F2D(RawBytecodeHelper.F2D, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#DOUBLE double} to {@link TypeKind#INT int}.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.d2i <em>d2i</em>
     */
    D2I(RawBytecodeHelper.D2I, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#DOUBLE double} to {@link TypeKind#LONG long}.
     *
     * @jvms 6.5.d2l <em>d2l</em>
     */
    D2L(RawBytecodeHelper.D2L, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#DOUBLE double} to {@link TypeKind#FLOAT float}.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.d2f <em>d2f</em>
     */
    D2F(RawBytecodeHelper.D2F, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#BYTE byte}.
     * This is as if storing the {@linkplain TypeKind##computational-type
     * computational} {@code int} into a {@code byte} and loading it back.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.i2b <em>i2b</em>
     */
    I2B(RawBytecodeHelper.I2B, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#CHAR char}.
     * This is as if storing the {@linkplain TypeKind##computational-type
     * computational} {@code int} into a {@code char} and loading it back.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.i2c <em>i2c</em>
     */
    I2C(RawBytecodeHelper.I2C, 1, Kind.CONVERT),

    /**
     * Convert {@link TypeKind#INT int} to {@link TypeKind#SHORT short}.
     * This is as if storing the {@linkplain TypeKind##computational-type
     * computational} {@code int} into a {@code short} and loading it back.
     *
     * @jls 5.1.3 Narrowing Primitive Conversion
     * @jvms 6.5.i2s <em>i2s</em>
     */
    I2S(RawBytecodeHelper.I2S, 1, Kind.CONVERT),

    /**
     * Compare {@link TypeKind#LONG long}.
     *
     * @see Long#compare(long, long)
     * @jvms 6.5.lcmp <em>lcmp</em>
     */
    LCMP(RawBytecodeHelper.LCMP, 1, Kind.OPERATOR),

    /**
     * Compare {@link TypeKind#FLOAT float}.
     * Produces {@code -1} if any operand is {@link Float#isNaN(float) NaN}.
     *
     * @see Double##equivalenceRelation Floating-point Equality, Equivalence, and Comparison
     * @jvms 6.5.fcmp_op <em>fcmp&lt;op&gt;</em>
     */
    FCMPL(RawBytecodeHelper.FCMPL, 1, Kind.OPERATOR),

    /**
     * Compare {@link TypeKind#FLOAT float}.
     * Produces {@code 1} if any operand is {@link Float#isNaN(float) NaN}.
     *
     * @see Double##equivalenceRelation Floating-point Equality, Equivalence, and Comparison
     * @jvms 6.5.fcmp_op <em>fcmp&lt;op&gt;</em>
     */
    FCMPG(RawBytecodeHelper.FCMPG, 1, Kind.OPERATOR),

    /**
     * Compare {@link TypeKind#DOUBLE double}.
     * Produces {@code -1} if any operand is {@link Double#isNaN(double) NaN}.
     *
     * @see Double##equivalenceRelation Floating-point Equality, Equivalence, and Comparison
     * @jvms 6.5.dcmp_op <em>dcmp&lt;op&gt;</em>
     */
    DCMPL(RawBytecodeHelper.DCMPL, 1, Kind.OPERATOR),

    /**
     * Compare {@link TypeKind#DOUBLE double}.
     * Produces {@code 1} if any operand is {@link Double#isNaN(double) NaN}.
     *
     * @see Double##equivalenceRelation Floating-point Equality, Equivalence, and Comparison
     * @jvms 6.5.dcmp_op <em>dcmp&lt;op&gt;</em>
     */
    DCMPG(RawBytecodeHelper.DCMPG, 1, Kind.OPERATOR),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code == 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFEQ(RawBytecodeHelper.IFEQ, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code != 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFNE(RawBytecodeHelper.IFNE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code < 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFLT(RawBytecodeHelper.IFLT, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code >= 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFGE(RawBytecodeHelper.IFGE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code > 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFGT(RawBytecodeHelper.IFGT, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code <= 0} succeeds.
     *
     * @jvms 6.5.if_cond <em>if_&lt;cond&gt;</em>
     */
    IFLE(RawBytecodeHelper.IFLE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 == operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPEQ(RawBytecodeHelper.IF_ICMPEQ, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 != operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPNE(RawBytecodeHelper.IF_ICMPNE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 < operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPLT(RawBytecodeHelper.IF_ICMPLT, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 >= operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPGE(RawBytecodeHelper.IF_ICMPGE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 > operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPGT(RawBytecodeHelper.IF_ICMPGT, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#INT int} comparison {@code operand1 <= operand2} succeeds.
     *
     * @jvms 6.5.if_icmp_cond <em>if_icmp&lt;cond&gt;</em>
     */
    IF_ICMPLE(RawBytecodeHelper.IF_ICMPLE, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#REFERENCE reference} comparison
     * {@code operand1 == operand2} succeeds.
     *
     * @jvms 6.5.if_acmp_cond <em>if_acmp&lt;cond&gt;</em>
     */
    IF_ACMPEQ(RawBytecodeHelper.IF_ACMPEQ, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#REFERENCE reference} comparison
     * {@code operand1 != operand2} succeeds.
     *
     * @jvms 6.5.if_acmp_cond <em>if_acmp&lt;cond&gt;</em>
     */
    IF_ACMPNE(RawBytecodeHelper.IF_ACMPNE, 3, Kind.BRANCH),

    /**
     * Branch always.
     *
     * @jvms 6.5.goto <em>goto</em>
     */
    GOTO(RawBytecodeHelper.GOTO, 3, Kind.BRANCH),

    /**
     * Jump subroutine (discontinued); last used in major version {@value
     * ClassFile#JAVA_6_VERSION}.
     *
     * @see DiscontinuedInstruction.JsrInstruction
     * @jvms 4.9.1 Static Constraints
     * @jvms 6.5.jsr <em>jsr</em>
     */
    JSR(RawBytecodeHelper.JSR, 3, Kind.DISCONTINUED_JSR),

    /**
     * Return from subroutine (discontinued); last used in major version
     * {@value ClassFile#JAVA_6_VERSION}.
     *
     * @see DiscontinuedInstruction.RetInstruction
     * @jvms 4.9.1 Static Constraints
     * @jvms 6.5.ret <em>ret</em>
     */
    RET(RawBytecodeHelper.RET, 2, Kind.DISCONTINUED_RET),

    /**
     * Access jump table by index and jump.
     *
     * @jvms 6.5.tableswitch <em>tableswitch</em>
     */
    TABLESWITCH(RawBytecodeHelper.TABLESWITCH, -1, Kind.TABLE_SWITCH),

    /**
     * Access jump table by key match and jump.
     *
     * @jvms 6.5.lookupswitch <em>lookupswitch</em>
     */
    LOOKUPSWITCH(RawBytecodeHelper.LOOKUPSWITCH, -1, Kind.LOOKUP_SWITCH),

    /**
     * Return {@link TypeKind#INT int} from method.
     *
     * @jvms 6.5.ireturn <em>ireturn</em>
     */
    IRETURN(RawBytecodeHelper.IRETURN, 1, Kind.RETURN),

    /**
     * Return {@link TypeKind#LONG long} from method.
     *
     * @jvms 6.5.lreturn <em>lreturn</em>
     */
    LRETURN(RawBytecodeHelper.LRETURN, 1, Kind.RETURN),

    /**
     * Return {@link TypeKind#FLOAT float} from method.
     *
     * @jvms 6.5.freturn <em>freturn</em>
     */
    FRETURN(RawBytecodeHelper.FRETURN, 1, Kind.RETURN),

    /**
     * Return {@link TypeKind#DOUBLE double} from method.
     *
     * @jvms 6.5.dreturn <em>dreturn</em>
     */
    DRETURN(RawBytecodeHelper.DRETURN, 1, Kind.RETURN),

    /**
     * Return {@link TypeKind#REFERENCE reference} from method.
     *
     * @jvms 6.5.areturn <em>areturn</em>
     */
    ARETURN(RawBytecodeHelper.ARETURN, 1, Kind.RETURN),

    /**
     * Return {@link TypeKind#VOID void} from method.
     *
     * @jvms 6.5.return <em>return</em>
     */
    RETURN(RawBytecodeHelper.RETURN, 1, Kind.RETURN),

    /**
     * Get {@code static} field from class.
     *
     * @jvms 6.5.getstatic <em>getstatic</em>
     */
    GETSTATIC(RawBytecodeHelper.GETSTATIC, 3, Kind.FIELD_ACCESS),

    /**
     * Set {@code static} field in class.
     *
     * @jvms 6.5.putstatic <em>putstatic</em>
     */
    PUTSTATIC(RawBytecodeHelper.PUTSTATIC, 3, Kind.FIELD_ACCESS),

    /**
     * Fetch field from object.
     *
     * @jvms 6.5.getfield <em>getfield</em>
     */
    GETFIELD(RawBytecodeHelper.GETFIELD, 3, Kind.FIELD_ACCESS),

    /**
     * Set field in object.
     *
     * @jvms 6.5.putfield <em>putfield</em>
     */
    PUTFIELD(RawBytecodeHelper.PUTFIELD, 3, Kind.FIELD_ACCESS),

    /**
     * Invoke instance method; dispatch based on class.
     *
     * @jvms 6.5.invokevirtual <em>invokevirtual</em>
     */
    INVOKEVIRTUAL(RawBytecodeHelper.INVOKEVIRTUAL, 3, Kind.INVOKE),

    /**
     * Invoke instance method; direct invocation of instance initialization
     * methods and methods of the current class and its supertypes.
     *
     * @jvms 6.5.invokevirtual <em>invokevirtual</em>
     */
    INVOKESPECIAL(RawBytecodeHelper.INVOKESPECIAL, 3, Kind.INVOKE),

    /**
     * Invoke a class ({@code static}) method.
     *
     * @jvms 6.5.invokestatic <em>invokestatic</em>
     */
    INVOKESTATIC(RawBytecodeHelper.INVOKESTATIC, 3, Kind.INVOKE),

    /**
     * Invoke interface method.
     *
     * @jvms 6.5.invokeinterface <em>invokeinterface</em>
     */
    INVOKEINTERFACE(RawBytecodeHelper.INVOKEINTERFACE, 5, Kind.INVOKE),

    /**
     * Invoke a dynamically-computed call site.
     *
     * @jvms 6.5.invokedynamic <em>invokedynamic</em>
     */
    INVOKEDYNAMIC(RawBytecodeHelper.INVOKEDYNAMIC, 5, Kind.INVOKE_DYNAMIC),

    /**
     * Create new object.
     *
     * @jvms 6.5.new <em>new</em>
     */
    NEW(RawBytecodeHelper.NEW, 3, Kind.NEW_OBJECT),

    /**
     * Create new array.
     *
     * @jvms 6.5.newarray <em>newarray</em>
     */
    NEWARRAY(RawBytecodeHelper.NEWARRAY, 2, Kind.NEW_PRIMITIVE_ARRAY),

    /** Create new array of {@link TypeKind#REFERENCE reference}.
     *
     * @jvms 6.5.anewarray <em>anewarray</em>
     */
    ANEWARRAY(RawBytecodeHelper.ANEWARRAY, 3, Kind.NEW_REF_ARRAY),

    /**
     * Get length of array.
     *
     * @jvms 6.5.arraylength <em>arraylength</em>
     */
    ARRAYLENGTH(RawBytecodeHelper.ARRAYLENGTH, 1, Kind.OPERATOR),

    /**
     * Throw exception or error.
     *
     * @jvms 6.5.athrow <em>athrow</em>
     */
    ATHROW(RawBytecodeHelper.ATHROW, 1, Kind.THROW_EXCEPTION),

    /**
     * Check whether object is of given type.
     *
     * @see Class#cast(Object)
     * @jvms 6.5.checkcast <em>checkcast</em>
     */
    CHECKCAST(RawBytecodeHelper.CHECKCAST, 3, Kind.TYPE_CHECK),

    /**
     * Determine if object is of given type.
     *
     * @see Class#isInstance(Object)
     * @jvms 6.5.instanceof <em>instanceof</em>
     */
    INSTANCEOF(RawBytecodeHelper.INSTANCEOF, 3, Kind.TYPE_CHECK),

    /**
     * Enter monitor for object.
     *
     * @jvms 6.5.monitorenter <em>monitorenter</em>
     */
    MONITORENTER(RawBytecodeHelper.MONITORENTER, 1, Kind.MONITOR),

    /**
     * Exit monitor for object.
     *
     * @jvms 6.5.monitorexit <em>monitorexit</em>
     */
    MONITOREXIT(RawBytecodeHelper.MONITOREXIT, 1, Kind.MONITOR),

    /**
     * Create new multidimensional array.
     *
     * @jvms 6.5.multianewarray <em>multianewarray</em>
     */
    MULTIANEWARRAY(RawBytecodeHelper.MULTIANEWARRAY, 4, Kind.NEW_MULTI_ARRAY),

    /**
     * Branch if {@link TypeKind#REFERENCE reference} is {@code null}.
     *
     * @jvms 6.5.ifnull <em>ifnull</em>
     */
    IFNULL(RawBytecodeHelper.IFNULL, 3, Kind.BRANCH),

    /**
     * Branch if {@link TypeKind#REFERENCE reference} is not {@code null}.
     *
     * @jvms 6.5.ifnonnull <em>ifnonnull</em>
     */
    IFNONNULL(RawBytecodeHelper.IFNONNULL, 3, Kind.BRANCH),

    /**
     * Branch always (wide index).
     *
     * @jvms 6.5.goto_w <em>goto_w</em>
     */
    GOTO_W(RawBytecodeHelper.GOTO_W, 5, Kind.BRANCH),

    /**
     * Jump subroutine (wide index) (discontinued); last used in major
     * version {@value ClassFile#JAVA_6_VERSION}.
     *
     * @see DiscontinuedInstruction.JsrInstruction
     * @jvms 4.9.1 Static Constraints
     * @jvms 6.5.jsr_w <em>jsr_w</em>
     */
    JSR_W(RawBytecodeHelper.JSR_W, 5, Kind.DISCONTINUED_JSR),

    /**
     * Load {@link TypeKind#INT int} from local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.iload <em>iload</em>
     */
    ILOAD_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.ILOAD, 4, Kind.LOAD),

    /**
     * Load {@link TypeKind#LONG long} from local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.lload <em>lload</em>
     */
    LLOAD_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.LLOAD, 4, Kind.LOAD),

    /**
     * Load {@link TypeKind#FLOAT float} from local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.fload <em>fload</em>
     */
    FLOAD_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.FLOAD, 4, Kind.LOAD),

    /**
     * Load {@link TypeKind#DOUBLE double} from local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.dload <em>dload</em>
     */
    DLOAD_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.DLOAD, 4, Kind.LOAD),

    /**
     * Load {@link TypeKind#REFERENCE reference} from local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.aload <em>aload</em>
     */
    ALOAD_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.ALOAD, 4, Kind.LOAD),

    /**
     * Store {@link TypeKind#INT int} into local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.istore <em>istore</em>
     */
    ISTORE_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.ISTORE, 4, Kind.STORE),

    /**
     * Store {@link TypeKind#LONG long} into local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.lstore <em>lstore</em>
     */
    LSTORE_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.LSTORE, 4, Kind.STORE),

    /**
     * Store {@link TypeKind#FLOAT float} into local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.fstore <em>fstore</em>
     */
    FSTORE_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.FSTORE, 4, Kind.STORE),

    /**
     * Store {@link TypeKind#DOUBLE double} into local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.dstore <em>dstore</em>
     */
    DSTORE_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.DSTORE, 4, Kind.STORE),

    /**
     * Store {@link TypeKind#REFERENCE reference} into local variable (wide index).
     * This is a {@linkplain #isWide() wide}-modified pseudo-opcode.
     * Can also store the {@code returnAddress} type.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.astore <em>astore</em>
     */
    ASTORE_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.ASTORE, 4, Kind.STORE),

    /**
     * Return from subroutine (wide index) (discontinued); last used in major
     * version {@value ClassFile#JAVA_6_VERSION}.
     *
     * @see DiscontinuedInstruction.RetInstruction
     * @jvms 4.9.1 Static Constraints
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.ret <em>ret</em>
     */
    RET_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.RET, 4, Kind.DISCONTINUED_RET),

    /**
     * Increment local variable by constant (wide index).
     *
     * @jvms 6.5.wide <em>wide</em>
     * @jvms 6.5.iinc <em>iinc</em>
     */
    IINC_W((RawBytecodeHelper.WIDE << 8) | RawBytecodeHelper.IINC, 6, Kind.INCREMENT);

    /**
     * Kinds of opcodes.
     *
     * @since 24
     */
    public enum Kind {

        /**
         * Load from local variable.
         *
         * @see LoadInstruction
         * @see Opcode#ILOAD
         * @see Opcode#LLOAD
         * @see Opcode#FLOAD
         * @see Opcode#DLOAD
         * @see Opcode#ALOAD
         * @see Opcode#ILOAD_0
         * @see Opcode#ILOAD_1
         * @see Opcode#ILOAD_2
         * @see Opcode#ILOAD_3
         * @see Opcode#LLOAD_0
         * @see Opcode#LLOAD_1
         * @see Opcode#LLOAD_2
         * @see Opcode#LLOAD_3
         * @see Opcode#FLOAD_0
         * @see Opcode#FLOAD_1
         * @see Opcode#FLOAD_2
         * @see Opcode#FLOAD_3
         * @see Opcode#DLOAD_0
         * @see Opcode#DLOAD_1
         * @see Opcode#DLOAD_2
         * @see Opcode#DLOAD_3
         * @see Opcode#ALOAD_0
         * @see Opcode#ALOAD_1
         * @see Opcode#ALOAD_2
         * @see Opcode#ALOAD_3
         * @see Opcode#ILOAD_W
         * @see Opcode#LLOAD_W
         * @see Opcode#FLOAD_W
         * @see Opcode#DLOAD_W
         * @see Opcode#ALOAD_W
         */
        LOAD,

        /**
         * Store into local variable.
         *
         * @see StoreInstruction
         * @see Opcode#ISTORE
         * @see Opcode#LSTORE
         * @see Opcode#FSTORE
         * @see Opcode#DSTORE
         * @see Opcode#ASTORE
         * @see Opcode#ISTORE_0
         * @see Opcode#ISTORE_1
         * @see Opcode#ISTORE_2
         * @see Opcode#ISTORE_3
         * @see Opcode#LSTORE_0
         * @see Opcode#LSTORE_1
         * @see Opcode#LSTORE_2
         * @see Opcode#LSTORE_3
         * @see Opcode#FSTORE_0
         * @see Opcode#FSTORE_1
         * @see Opcode#FSTORE_2
         * @see Opcode#FSTORE_3
         * @see Opcode#DSTORE_0
         * @see Opcode#DSTORE_1
         * @see Opcode#DSTORE_2
         * @see Opcode#DSTORE_3
         * @see Opcode#ASTORE_0
         * @see Opcode#ASTORE_1
         * @see Opcode#ASTORE_2
         * @see Opcode#ASTORE_3
         * @see Opcode#ISTORE_W
         * @see Opcode#LSTORE_W
         * @see Opcode#FSTORE_W
         * @see Opcode#DSTORE_W
         * @see Opcode#ASTORE_W
         */
        STORE,

        /**
         * Increment local variable.
         *
         * @see IncrementInstruction
         * @see Opcode#IINC
         * @see Opcode#IINC_W
         */
        INCREMENT,

        /**
         * Branch.
         *
         * @see BranchInstruction
         * @see Opcode#IFEQ
         * @see Opcode#IFNE
         * @see Opcode#IFLT
         * @see Opcode#IFGE
         * @see Opcode#IFGT
         * @see Opcode#IFLE
         * @see Opcode#IF_ICMPEQ
         * @see Opcode#IF_ICMPNE
         * @see Opcode#IF_ICMPLT
         * @see Opcode#IF_ICMPGE
         * @see Opcode#IF_ICMPGT
         * @see Opcode#IF_ICMPLE
         * @see Opcode#IF_ACMPEQ
         * @see Opcode#IF_ACMPNE
         * @see Opcode#GOTO
         * @see Opcode#IFNULL
         * @see Opcode#IFNONNULL
         * @see Opcode#GOTO_W
         */
        BRANCH,

        /**
         * Access jump table by key match and jump.
         *
         * @see LookupSwitchInstruction
         * @see Opcode#LOOKUPSWITCH
         */
        LOOKUP_SWITCH,

        /**
         * Access jump table by index and jump.
         *
         * @see TableSwitchInstruction
         * @see Opcode#TABLESWITCH
         */
        TABLE_SWITCH,

        /**
         * Return from method.
         *
         * @see ReturnInstruction
         * @see Opcode#IRETURN
         * @see Opcode#LRETURN
         * @see Opcode#FRETURN
         * @see Opcode#DRETURN
         * @see Opcode#ARETURN
         * @see Opcode#RETURN
         */
        RETURN,

        /**
         * Throw exception or error.
         *
         * @see ThrowInstruction
         * @see Opcode#ATHROW
         */
        THROW_EXCEPTION,

        /**
         * Access field.
         *
         * @see FieldInstruction
         * @see Opcode#GETSTATIC
         * @see Opcode#PUTSTATIC
         * @see Opcode#GETFIELD
         * @see Opcode#PUTFIELD
         */
        FIELD_ACCESS,

        /**
         * Invoke method or constructor.
         *
         * @see InvokeInstruction
         * @see Opcode#INVOKEVIRTUAL
         * @see Opcode#INVOKESPECIAL
         * @see Opcode#INVOKESTATIC
         * @see Opcode#INVOKEINTERFACE
         */
        INVOKE,

        /**
         * Invoke a dynamically-computed call site.
         *
         * @see InvokeDynamicInstruction
         * @see Opcode#INVOKEDYNAMIC
         */
        INVOKE_DYNAMIC,

        /**
         * Create new object.
         *
         * @see NewObjectInstruction
         * @see Opcode#NEW
         */
        NEW_OBJECT,

        /**
         * Create new array.
         *
         * @see NewPrimitiveArrayInstruction
         * @see Opcode#NEWARRAY
         */
        NEW_PRIMITIVE_ARRAY,

        /**
         * Create new {@link TypeKind#REFERENCE reference} array.
         *
         * @see NewReferenceArrayInstruction
         * @see Opcode#ANEWARRAY
         */
        NEW_REF_ARRAY,

        /**
         * Create new multidimensional array.
         *
         * @see NewMultiArrayInstruction
         * @see Opcode#MULTIANEWARRAY
         */
        NEW_MULTI_ARRAY,

        /**
         * Check whether object is of given type.
         *
         * @see TypeCheckInstruction
         * @see Opcode#CHECKCAST
         * @see Opcode#INSTANCEOF
         */
        TYPE_CHECK,

        /**
         * Load from array.
         *
         * @see ArrayLoadInstruction
         * @see Opcode#IALOAD
         * @see Opcode#LALOAD
         * @see Opcode#FALOAD
         * @see Opcode#DALOAD
         * @see Opcode#AALOAD
         * @see Opcode#BALOAD
         * @see Opcode#CALOAD
         * @see Opcode#SALOAD
         */
        ARRAY_LOAD,

        /**
         * Store into array.
         *
         * @see ArrayStoreInstruction
         * @see Opcode#IASTORE
         * @see Opcode#LASTORE
         * @see Opcode#FASTORE
         * @see Opcode#DASTORE
         * @see Opcode#AASTORE
         * @see Opcode#BASTORE
         * @see Opcode#CASTORE
         * @see Opcode#SASTORE
         */
        ARRAY_STORE,

        /**
         * Stack operations.
         *
         * @see StackInstruction
         * @see Opcode#POP
         * @see Opcode#POP2
         * @see Opcode#DUP
         * @see Opcode#DUP_X1
         * @see Opcode#DUP_X2
         * @see Opcode#DUP2
         * @see Opcode#DUP2_X1
         * @see Opcode#DUP2_X2
         * @see Opcode#SWAP
         */
        STACK,

        /**
         * Type conversions.
         *
         * @see ConvertInstruction
         * @see Opcode#I2L
         * @see Opcode#I2F
         * @see Opcode#I2D
         * @see Opcode#L2I
         * @see Opcode#L2F
         * @see Opcode#L2D
         * @see Opcode#F2I
         * @see Opcode#F2L
         * @see Opcode#F2D
         * @see Opcode#D2I
         * @see Opcode#D2L
         * @see Opcode#D2F
         * @see Opcode#I2B
         * @see Opcode#I2C
         * @see Opcode#I2S
         */
        CONVERT,

        /**
         * Operators.
         *
         * @see OperatorInstruction
         * @see Opcode#IADD
         * @see Opcode#LADD
         * @see Opcode#FADD
         * @see Opcode#DADD
         * @see Opcode#ISUB
         * @see Opcode#LSUB
         * @see Opcode#FSUB
         * @see Opcode#DSUB
         * @see Opcode#IMUL
         * @see Opcode#LMUL
         * @see Opcode#FMUL
         * @see Opcode#DMUL
         * @see Opcode#IDIV
         * @see Opcode#LDIV
         * @see Opcode#FDIV
         * @see Opcode#DDIV
         * @see Opcode#IREM
         * @see Opcode#LREM
         * @see Opcode#FREM
         * @see Opcode#DREM
         * @see Opcode#INEG
         * @see Opcode#LNEG
         * @see Opcode#FNEG
         * @see Opcode#DNEG
         * @see Opcode#ISHL
         * @see Opcode#LSHL
         * @see Opcode#ISHR
         * @see Opcode#LSHR
         * @see Opcode#IUSHR
         * @see Opcode#LUSHR
         * @see Opcode#IAND
         * @see Opcode#LAND
         * @see Opcode#IOR
         * @see Opcode#LOR
         * @see Opcode#IXOR
         * @see Opcode#LXOR
         * @see Opcode#LCMP
         * @see Opcode#FCMPL
         * @see Opcode#FCMPG
         * @see Opcode#DCMPL
         * @see Opcode#DCMPG
         * @see Opcode#ARRAYLENGTH
         */
        OPERATOR,

        /**
         * Constants.
         *
         * @see ConstantInstruction
         * @see Opcode#ACONST_NULL
         * @see Opcode#ICONST_M1
         * @see Opcode#ICONST_0
         * @see Opcode#ICONST_1
         * @see Opcode#ICONST_2
         * @see Opcode#ICONST_3
         * @see Opcode#ICONST_4
         * @see Opcode#ICONST_5
         * @see Opcode#LCONST_0
         * @see Opcode#LCONST_1
         * @see Opcode#FCONST_0
         * @see Opcode#FCONST_1
         * @see Opcode#FCONST_2
         * @see Opcode#DCONST_0
         * @see Opcode#DCONST_1
         * @see Opcode#BIPUSH
         * @see Opcode#SIPUSH
         * @see Opcode#LDC
         * @see Opcode#LDC_W
         * @see Opcode#LDC2_W
         */
        CONSTANT,

        /**
         * Monitor.
         *
         * @see MonitorInstruction
         * @see Opcode#MONITORENTER
         * @see Opcode#MONITOREXIT
         */
        MONITOR,

        /**
         * Do nothing.
         *
         * @see NopInstruction
         * @see Opcode#NOP
         */
        NOP,

        /**
         * Discontinued jump subroutine.
         *
         * @see DiscontinuedInstruction.JsrInstruction
         * @see Opcode#JSR
         * @see Opcode#JSR_W
         */
        DISCONTINUED_JSR,

        /**
         * Discontinued return from subroutine.
         *
         * @see DiscontinuedInstruction.RetInstruction
         * @see Opcode#RET
         * @see Opcode#RET_W
         */
        DISCONTINUED_RET;
    }

    private final int bytecode;
    private final int sizeIfFixed;
    private final Kind kind;

    Opcode(int bytecode, int sizeIfFixed, Kind kind) {
        this.bytecode = bytecode;
        this.sizeIfFixed = sizeIfFixed;
        this.kind = kind;
    }

    /**
     * {@return the opcode value} For {@linkplain #isWide() wide} pseudo-opcodes, returns the
     * first 2 bytes of the instruction, which are the wide opcode {@code 196} ({@code 0xC4})
     * and the functional opcode, as a U2 value.
     */
    public int bytecode() { return bytecode; }

    /**
     * {@return true if this is a pseudo-opcode modified by wide opcode}
     * <p>
     * {@code wide} extends local variable index by additional bytes.
     *
     * @jvms 6.5.wide <em>wide</em>
     * @see #ILOAD_W
     * @see #LLOAD_W
     * @see #FLOAD_W
     * @see #DLOAD_W
     * @see #ALOAD_W
     * @see #ISTORE_W
     * @see #LSTORE_W
     * @see #FSTORE_W
     * @see #DSTORE_W
     * @see #ASTORE_W
     * @see #RET_W
     * @see #IINC_W
     */
    public boolean isWide() { return bytecode > 255; }

    /**
     * {@return size of the instruction in bytes if fixed, or -1 otherwise} This size includes
     * the opcode itself.
     *
     * @see Instruction#sizeInBytes() Instruction::sizeInBytes
     */
    public int sizeIfFixed() { return sizeIfFixed; }

    /**
     * {@return instruction kind}
     */
    public Kind kind() { return kind; }
}
