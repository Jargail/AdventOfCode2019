package com.jonas.AdventOfCode2019.Day4;

import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public void run1(Integer lowerRange, Integer upperRange) {

        List<Integer> lstMatches = new ArrayList<>();
        for(; lowerRange <= upperRange; lowerRange++) {
            if (rule1(lowerRange) && rule2(lowerRange))
                lstMatches.add(lowerRange);
        }
        System.out.println(lstMatches);
        System.out.println("Antal: " + lstMatches.size());
    }

    public void run2(Integer lowerRange, Integer upperRange) {

        List<Integer> lstMatches = new ArrayList<>();
        for(; lowerRange <= upperRange; lowerRange++) {
            if (rule1second(lowerRange) && rule2(lowerRange))
                lstMatches.add(lowerRange);
        }
        //System.out.println(lstMatches);
        System.out.println("Antal: " + lstMatches.size());
    }

    private boolean rule1(Integer number) {
        boolean ret = false;
        String strNum = number.toString();

        String subNumber = "";
        for(int i = 0; i < 5; ++i) {
            subNumber = strNum.substring(i, i+2);
            if (Integer.parseInt(subNumber) % 11 == 0) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    private boolean rule1second(Integer number) {
        boolean ret = false;
        String strNum = number.toString();

        String subNumber = "";
        String controlNumber = "";
        for(int i = 0; i < 5; ++i) {
            controlNumber = strNum.substring(i, i+1);
            subNumber = strNum.substring(i, i+2);
            if (Integer.parseInt(subNumber) % 11 == 0) {
                if (i == 0) {
                    if (!controlNumber.equals(strNum.substring(i + 2, i + 3))) {
                        ret = true;
                        break;
                    }
                }else if (i < 4 && i > 0) {
                    if (!controlNumber.equals(strNum.substring(i+2, i+3))
                    && !controlNumber.equals(strNum.substring(i-1, i))) {
                        ret = true;
                        break;
                    }
                } else if (i == 4 ){
                    if (!controlNumber.equals(strNum.substring(i-1, i))) {
                        ret = true;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    private boolean rule2(Integer number) {
        boolean ret = true;
        String strNum = number.toString();
        for (int i = 0; i < 5; ++i) {
            String first = strNum.substring(i, i+1);
            String second = strNum.substring(i+1, i+2);
            if (Integer.parseInt(first) > Integer.parseInt(second)) {
                ret = false;
                break;
            }
        }
        return ret;
    }
}
