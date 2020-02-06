package com.jonas.AdventOfCode2019.Day7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Day7IntComputer {

    int instrPointer = 0;
    boolean break99 = false;
    int[] input;
    int phase;
    int nrInput = 0;

    public Day7IntComputer(int[] input, int phase) {
        this.input = input;
        this.phase = phase;
    }

    public int computer(int signal) {

        int instruction;
        int val1, val2, val3;
        int param1, param2, param3, ret = 0;

        boolean cont;
        cont = true;
        while(instrPointer < input.length && cont) {
            instruction = input[instrPointer++];
            Instruction instr = new Instruction(instruction);

            switch (instr.opcode) {
                case 1:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    input[param3] = val1 + val2;
                    break;
                case 2:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    input[param3] = val1 * val2;
                    break;
                case 3:
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    param1 = input[instrPointer++];
                    if (nrInput == 0)
                        input[param1] = phase;
                    else
                        input[param1] = signal;
                    nrInput++;
                    break;
                case 4:
                    param1 = input[instrPointer++];
                    ret = input[param1];
                    cont = false;
                    break;
                case 5:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    if (val1 != 0)
                        instrPointer = val2;
                    break;
                case 6:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    if (val1 == 0)
                        instrPointer = val2;
                    break;
                case 7:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    if (val1 < val2)
                        input[param3] = 1;
                    else
                        input[param3] = 0;
                    break;
                case 8:
                    param1 = input[instrPointer++];
                    param2 = input[instrPointer++];
                    param3 = input[instrPointer++];
                    val1 = instr.paramMode1 == 0 ? input[param1] : param1;
                    val2 = instr.paramMode2 == 0 ? input[param2] : param2;
                    if (val1 == val2)
                        input[param3] = 1;
                    else
                        input[param3] = 0;
                    break;
                case 99:
                    cont = false;
                    break99 = true;
                    break;
                default:
                    System.out.println("Faulty option");
                    cont = false;
                    break;
            }
        }
        return ret;
    }

    private class Instruction {
        int opcode;
        int paramMode1;
        int paramMode2;
        int paramMode3;

        public Instruction(int instruction) {
            opcode = instruction % 100;
            paramMode1 = instruction / 100 % 10;
            paramMode2 = instruction / 1000 % 10;
            paramMode3 = instruction / 10000 % 10;
        }
    }
}
