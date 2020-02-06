package com.jonas.AdventOfCode2019.Day9;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public void run1(long[] input) {
        List<Long> lst = new ArrayList<>();
        Day9IntComputer computer = new Day9IntComputer(input, 1);
        while (!computer.break99) {
            long ret = computer.computer(1);
            if (!computer.break99)
                lst.add(ret);
        }
        System.out.println(lst.toString());
    }
}
