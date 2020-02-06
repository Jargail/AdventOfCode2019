package com.jonas.AdventOfCode2019.Day10;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day10 {
    private class Asteroid {
        Pair<Integer, Integer> pos;
        int numLineOfSight;
    }

    private List<Asteroid> asteroids = new ArrayList<>();
    private List<Pair<Integer, Integer>> edge = new ArrayList<>();
    private char[][] input;

    public void run1(char[][] input) {
        this.input = input;

        getAsteroids();
        getLOS();

        //asteroids.forEach(a -> System.out.println(a.numLineOfSight));
        Asteroid best = asteroids.get(0);
        for (Asteroid a: asteroids) {
            if (best.numLineOfSight < a.numLineOfSight)
                best = a;
        }
        System.out.println("("+best.pos.getKey() + "," + best.pos.getValue() + ") " + best.numLineOfSight);
    }

    private void getAsteroids() {
        /* Loop thru whole map */
        for(int y=0; y<input.length; ++y) {
            for(int x=0; x<input[y].length; ++x) {
                if (input[y][x] == '#') {
                    Asteroid current = new Asteroid();
                    current.pos = new Pair<>(x, y);
                    asteroids.add(current);
                }
            }
        }
    }

    private void getLOS() {
        Asteroid current;
        Asteroid compare;
        Boolean before;
        for (int a = 0; a < asteroids.size(); ++a) {
            current = asteroids.get(a);
            List<Pair<Double, Boolean>> slopes = new ArrayList<>();
            for (int b = 0; b < asteroids.size(); ++b){
                compare = asteroids.get(b);
                if (!current.pos.equals(compare.pos)) {
                    double slope = getSlope(current.pos, compare.pos);
                    before = false;
                    if (compare.pos.getValue() < current.pos.getValue())
                        before = true;
                    Pair<Double, Boolean> slopeY = new Pair<>(slope, before);


                    if (!slopes.contains(slopeY)) {
                        slopes.add(slopeY);
                        current.numLineOfSight++;
                    }
                }
            }
        }
    }

    private double getSlope(Pair<Integer, Integer> a1, Pair<Integer, Integer> a2) {
        return ((double) a1.getValue() - (double) a2.getValue()) / ((double) a1.getKey() - (double) a2.getKey());
    }

    private void getEgde() {
        // Get all y edge positions
        for (int y = 0; y < input.length; ++y) {
            Pair<Integer, Integer> pair1 = new Pair<>(0, y);
            Pair<Integer, Integer> pair2 = new Pair<>(input[0].length - 1, y);
            edge.add(pair1);
            edge.add(pair2);
        }

        for (int x = 1; x < input[0].length - 1; ++x) {
            Pair<Integer, Integer> pair1 = new Pair<>(x, 0);
            Pair<Integer, Integer> pair2 = new Pair<>(x, input.length - 1);
            edge.add(pair1);
            edge.add(pair2);
        }
    }

}
