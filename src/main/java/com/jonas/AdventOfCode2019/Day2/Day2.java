package com.jonas.AdventOfCode2019.Day2;

public class Day2 {
    public static final int[] inp1 = {1,9,10,3,2,3,11,0,99,30,40,50};
    public static final int[] inp2 = {2,3,0,3,99};
    public static final int[] inp3 = {2,4,4,5,99,0};
    public static final int[] inp4 = {1,1,1,4,99,5,6,0,99};
    public static final int[] puzzle1 = {1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,6,23,1,23,6,27,1,13,27,31,2,13,31,35,1,5,35,39,2,39,13,43,1,10,43,47,2,13,47,51,1,6,51,55,2,55,13,59,1,59,10,63,1,63,10,67,2,10,67,71,1,6,71,75,1,10,75,79,1,79,9,83,2,83,6,87,2,87,9,91,1,5,91,95,1,6,95,99,1,99,9,103,2,10,103,107,1,107,6,111,2,9,111,115,1,5,115,119,1,10,119,123,1,2,123,127,1,127,6,0,99,2,14,0,0};

    public int computer(int[] input) {

        int opcode;
        int pos1;
        int pos2;
        int pos3;
        if (input.length < 4)
            System.out.println("Too few values");

        Boolean cont = true;
        int i=0;
        while(i < input.length - 1 && cont) {
            opcode = input[i++];
            if (opcode == 99)
                break;
            pos1 = input[i++];
            pos2 = input[i++];
            pos3 = input[i++];

            if( pos1 >= input.length
                    || pos2 >= input.length
                    || pos3 >= input.length) {
                System.out.println("Out of bounds");
                break;
            }

            switch (opcode) {
                case 1:
                    input[pos3] = input[pos1] + input[pos2];
                    break;
                case 2:
                    input[pos3] = input[pos1] * input[pos2];
                    break;
                default:
                    System.out.println("Faulty option");
                    cont = false;
                    break;
            }
        }
        return input[0];
    }
}
