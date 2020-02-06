package com.jonas.AdventOfCode2019.Day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Day13IntComputer {

    int instrPointer = 0;
    boolean break99 = false;
    long[] input;
    long phase;
    long nrInput = 0, relBase = 0;

    public Day13IntComputer(long[] input, long phase) {
        this.input = input;
        this.phase = phase;
    }

    public long computer(long signal) {

        long instruction;
        long val1, val2, val3;
        long param1, param2, param3, ret = 0;

        Boolean cont = true;
        while(instrPointer < input.length && cont) {
            instruction = input[instrPointer++];
            Instruction instr = new Instruction(instruction);

            switch ((int) instr.opcode) {
                case 1:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    setValue(val1 + val2, relBase, param3, instr.paramMode3);
                    break;
                case 2:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    setValue(val1 * val2, relBase, param3, instr.paramMode3);
                    break;
                case 3:
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    param1 = input[instrPointer++];
                    try {
                        //int cmdIn = Integer.parseInt(reader.readLine());
                        long cmdIn = 2L;//Long.parseLong(reader.readLine());
                        setValue(cmdIn, relBase, param1, instr.paramMode1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    param1 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    ret = val1;
                    cont = false;
                    break;
                case 5:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    if (val1 != 0)
                        instrPointer = (int) val2;
                    break;
                case 6:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    if (val1 == 0)
                        instrPointer = (int) val2;
                    break;
                case 7:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    if (val1 < val2)
                        setValue(1L, relBase, param3, instr.paramMode3);
                    else
                        setValue(0L, relBase, param3, instr.paramMode3);
                    break;
                case 8:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    val2 = getValue(relBase, param2, instr.paramMode2);
                    if (val1 == val2)
                        setValue(1L, relBase, param3, instr.paramMode3);
                    else
                        setValue(0L, relBase, param3, instr.paramMode3);
                    break;
                case 9:
                    param1 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    relBase += val1;
                    break;
                case 99:
                    cont = false;
                    break99 = true;
                    break;
                default:
                    System.out.println("Faulty option");
                    cont = false;
                    break99 = true;
                    break;
            }
        }

        return ret;
    }

    private long getValue(long relBase, long param, long paramMode) {
        long val;
        if (paramMode == 0)
            val = input[(int) param];
        else if (paramMode == 1)
            val = param;
        else
            val = input[(int) param + (int) relBase];
        return val;
    }

    private void setValue(long value, long relBase, long param, long paramMode) {
        if (paramMode == 0) {
            input[(int) param] = value;
        }
        else if (paramMode == 1) {
            long pos = input[(int) param];
            input[(int) pos] = value;
        }
        else {
            input[(int) param + (int) relBase] = value;
        }
    }

    private class Instruction {
        long opcode;
        long paramMode1;
        long paramMode2;
        long paramMode3;

        public Instruction(long instruction) {
            opcode = instruction % 100;
            paramMode1 = instruction / 100 % 10;
            paramMode2 = instruction / 1000 % 10;
            paramMode3 = instruction / 10000 % 10;
        }
    }
}
