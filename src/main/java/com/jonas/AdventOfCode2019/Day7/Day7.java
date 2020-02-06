package com.jonas.AdventOfCode2019.Day7;

import com.jonas.AdventOfCode2019.Data.DataDay7;

import java.util.ArrayList;
import java.util.List;

public class Day7 {

    public void runTests() {
        Day7IntComputer amp1 = new Day7IntComputer(DataDay7.INP5, DataDay7.PHASE5[0]);
        Day7IntComputer amp2 = new Day7IntComputer(DataDay7.INP5, DataDay7.PHASE5[1]);
        Day7IntComputer amp3 = new Day7IntComputer(DataDay7.INP5, DataDay7.PHASE5[2]);
        Day7IntComputer amp4 = new Day7IntComputer(DataDay7.INP5, DataDay7.PHASE5[3]);
        Day7IntComputer amp5 = new Day7IntComputer(DataDay7.INP5, DataDay7.PHASE5[4]);

        int signal = 0;
        int ret1 = 0;
        int ret2 = 0;
        int ret3 = 0;
        int ret4 = 0;
        int ret5 = 0;
        int i = 0;
        while (!amp1.break99 && !amp2.break99 && !amp3.break99 && !amp4.break99 && !amp5.break99) {
            if (!amp1.break99)
                ret1 = signal = amp1.computer(signal);
            if (!amp2.break99)
                ret2 = signal = amp2.computer(signal);
            if (!amp3.break99)
                ret3 = signal = amp3.computer(signal);
            if (!amp4.break99)
                ret4 = signal = amp4.computer(signal);
            if (!amp5.break99) {
                signal = amp5.computer(signal);
                if (!amp5.break99)
                    ret5 = signal;
            }
        }
        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);
        System.out.println(ret4);
        System.out.println(ret5);
    }

    public void run1() {

        int ret = 0;
        List<Integer> retPhase = new ArrayList<>();
        int signal = 0;
        for (int a=0; a<5; ++a)
            for (int b=0; b<5; ++b) {
                if (a == b)
                    continue;
                for (int c = 0; c < 5; ++c) {
                    if (a==c || b==c)
                        continue;;
                    for (int d = 0; d < 5; ++d) {
                        if (a==d || b==d || c==d)
                            continue;
                        for (int e = 0; e < 5; ++e) {
                            if (a==e || b==e || c==e || d==e)
                                continue;
                            signal = 0;
                            int[] phase = {a, b, c, d, e};
                            Day7IntComputer amp1 = new Day7IntComputer(DataDay7.INP3, phase[0]);
                            Day7IntComputer amp2 = new Day7IntComputer(DataDay7.INP3, phase[1]);
                            Day7IntComputer amp3 = new Day7IntComputer(DataDay7.INP3, phase[2]);
                            Day7IntComputer amp4 = new Day7IntComputer(DataDay7.INP3, phase[3]);
                            Day7IntComputer amp5 = new Day7IntComputer(DataDay7.INP3, phase[4]);

                            signal = amp1.computer(signal);
                            signal = amp2.computer(signal);
                            signal = amp3.computer(signal);
                            signal = amp4.computer(signal);
                            signal = amp5.computer(signal);

                            if (ret < signal) {
                                ret = signal;
                                retPhase.clear();
                                for (int item : phase)
                                    retPhase.add(item);
                            }
                        }
                    }
                }
            }
        System.out.println(ret);
        System.out.println(retPhase);
    }

    public void run2() {

        int ret = 0;
        List<Integer> retPhase = new ArrayList<>();
        int signal = 0;
        for (int a=5; a<10; ++a)
            for (int b=5; b<10; ++b) {
                if (a == b)
                    continue;
                for (int c = 5; c < 10; ++c) {
                    if (a==c || b==c)
                        continue;;
                    for (int d = 5; d < 10; ++d) {
                        if (a==d || b==d || c==d)
                            continue;
                        for (int e = 5; e < 10; ++e) {
                            if (a==e || b==e || c==e || d==e)
                                continue;
                            signal = 0;
                            int[] phase = {a, b, c, d, e};
                            Day7IntComputer amp1 = new Day7IntComputer(DataDay7.PUZZLE1, phase[0]);
                            Day7IntComputer amp2 = new Day7IntComputer(DataDay7.PUZZLE1, phase[1]);
                            Day7IntComputer amp3 = new Day7IntComputer(DataDay7.PUZZLE1, phase[2]);
                            Day7IntComputer amp4 = new Day7IntComputer(DataDay7.PUZZLE1, phase[3]);
                            Day7IntComputer amp5 = new Day7IntComputer(DataDay7.PUZZLE1, phase[4]);

                            System.out.println(a + " " + b + " " + c + " " + d + " " + e);
                            while (!amp1.break99 && !amp2.break99 && !amp3.break99 && !amp4.break99 && !amp5.break99) {

                                signal = amp1.computer(signal);
                                signal = amp2.computer(signal);
                                signal = amp3.computer(signal);
                                signal = amp4.computer(signal);
                                signal = amp5.computer(signal);
                                if (!amp5.break99) {
                                    if (ret < signal) {
                                        ret = signal;
                                        retPhase.clear();
                                        for (int item : phase)
                                            retPhase.add(item);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        System.out.println(ret);
        System.out.println(retPhase);
    }
}
