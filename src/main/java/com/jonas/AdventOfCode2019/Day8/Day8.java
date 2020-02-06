package com.jonas.AdventOfCode2019.Day8;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day8 {
    private int rows;
    private int columns;
    private ArrayList<ArrayList<Integer>> images = new ArrayList<>();

    public Day8(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void run1(String input) {
        convertToImages(input);
        ArrayList<Integer> fewestZeroesImage = findImageWithFewestZeroes();
        int ones = countOnes(fewestZeroesImage);
        int twos = countTwos(fewestZeroesImage);
        int mult = ones * twos;
        System.out.println(mult);
    }

    public void run2(String input) {
        convertToImages(input);
        ArrayList<Integer> lst = decodeImages();
        printImage(lst);
        System.out.println(lst);
    }

    private void convertToImages(String input) {
        int inpPointer = 0;
        while (inpPointer < input.length()) {
            ArrayList<Integer> image = new ArrayList<>();
            for (int i = 0; i < rows * columns; ++i) {
                if (i + inpPointer == input.length() - 1)
                    image.add(Integer.parseInt(input.substring(inpPointer + i)));
                else
                    image.add(Integer.parseInt(input.substring(inpPointer + i, inpPointer + i + 1)));
            }
            images.add(image);
            inpPointer += rows * columns;
        }
    }

    private ArrayList<Integer> findImageWithFewestZeroes() {
        ArrayList<Integer> fewestZeroImage = new ArrayList<>();
        int noZeros = 0;
        int ret = rows * columns;
        for (ArrayList<Integer> image : images) {
            noZeros = image.stream()
                    .filter( b -> b == 0)
                    .collect((Collectors.toList()))
                    .size();
            if (ret >= noZeros) {
                ret = noZeros;
                fewestZeroImage = image;
            }
        }
        return fewestZeroImage;
    }

    private int countOnes(ArrayList<Integer> image) {
        return image.stream()
                .filter((b -> b == 1))
                .collect(Collectors.toList())
                .size();
    }

    private int countTwos(ArrayList<Integer> image) {
        return image.stream()
                .filter((b -> b == 2))
                .collect(Collectors.toList())
                .size();
    }

    private ArrayList<Integer> decodeImages() {
        ArrayList<Integer> retLst = new ArrayList<>();
        for(int i = 0; i < rows * columns; ++i) {
            for(int j = 0; j < images.size(); ++j) {
                int ret = images.get(j).get(i);
                if (ret != 2) {
                    retLst.add(ret);
                    break;
                }
            }
        }
        return retLst;
    }

    private void printImage(ArrayList<Integer> image) {
        String row = "";
        for(int i=0; i<image.size(); ++i) {
            row += image.get(i).toString();
            if (row.length() == columns) {
                System.out.println(row);
                row = "";
            }
        }
    }
}
