package com.jonas.AdventOfCode2019.Day1;


import java.util.Arrays;
import java.util.List;

public class Day1 {

    public static final List<Integer> inputLst = Arrays.asList(
            77355,
            115734,
            59983,
            106798,
            71384,
            112431,
            87261,
            98469,
            104485,
            63185,
            112442,
            90113,
            62805,
            77610,
            61459,
            55290,
            139325,
            58463,
            65173,
            95550,
            101228,
            70912,
            147516,
            62547,
            137966,
            53801,
            115927,
            133275,
            147358,
            126852,
            110379,
            107234,
            130258,
            127847,
            118167,
            122223,
            90956,
            141688,
            88278,
            54049,
            135498,
            123187,
            125149,
            61475,
            136691,
            133089,
            120734,
            112196,
            88342,
            94531,
            105013,
            118379,
            106009,
            78690,
            87934,
            75396,
            83546,
            64225,
            104813,
            127819,
            78321,
            107227,
            107651,
            139758,
            50150,
            55272,
            106774,
            68290,
            104639,
            140973,
            121498,
            89391,
            108435,
            73725,
            51004,
            104700,
            127297,
            91490,
            103583,
            128041,
            146250,
            142082,
            95475,
            65298,
            130514,
            92002,
            141553,
            126533,
            75251,
            143249,
            146307,
            50681,
            128266,
            109199,
            72487,
            50416,
            92153,
            120627,
            119192,
            56510);

    public void puzzle1(List<Integer> lst) {
        Integer ret = lst.stream()
                .mapToInt(b -> b / 3 - 2)
                .sum();

        System.out.println("Answer puzzle 1: " + ret);
    }

    private Integer countSingle(Integer m) {
        Integer tmp = m / 3 - 2;
        if (tmp <= 0)
            return 0;
        else
            return tmp + countSingle(tmp);
    }

    public void puzzle2(List<Integer> lst) {
        Integer ret = lst.stream()
                .mapToInt(b -> countSingle(b))
                .sum();

        System.out.println("Answer puzzle 2: " + ret);
    }
}
