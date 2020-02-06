package com.jonas.AdventOfCode2019.Day10;

import javafx.util.Pair;

import java.util.*;

public class Day10_2 {
    private class Asteroid {
        Pair<Integer, Integer> pos;
        int numLineOfSight = 0;
        double distance = 0.0;
        Boolean before = false;
    }

    Map<Double, List<Asteroid>> asteroids;
    Map<Double, List<Asteroid>> negSlopes;
    Map<Double, List<Asteroid>> posSlopes;
    Pair<Integer, Integer> station;
    char[][] input;

    // Den går baklänges och skriver inte ut 2,0
    public void run(char[][] input, Pair<Integer, Integer> station) {
        this.input = input;
        this.station = station;
        createAsteroids();
        negSlopes = getNegSlopes();
        posSlopes = getPosSlopes();

        SortedSet<Double> sortedNegSlopes = new TreeSet<>(negSlopes.keySet());
        SortedSet<Double> sortedPosSlopes = new TreeSet<>(posSlopes.keySet());

        int i = 0;
        Asteroid current = null;
        Double sRem = null;
        while((!sortedNegSlopes.isEmpty() || !sortedPosSlopes.isEmpty()) && i < 201) {
            if (sortedPosSlopes.contains(Double.POSITIVE_INFINITY)) {
                List<Asteroid> alst = posSlopes.get(Double.POSITIVE_INFINITY);
                for (Asteroid a : alst) {
                    if (current == null)
                        current = a;
                    else if (current.distance > a.distance)
                        current = a;
                }
                alst.remove(current);
                if (alst.isEmpty())
                    sRem = Double.POSITIVE_INFINITY;
                ++i;
                if (i == 200) {
                    printAsteroid(current);
                    break;
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedPosSlopes.remove(sRem);
            sRem = null;


            if (!sortedNegSlopes.isEmpty()) {
                for (Double s : sortedNegSlopes) {
                    current = null;
                    if (!s.equals(Double.NEGATIVE_INFINITY)) {
                        List<Asteroid> alst = negSlopes.get(s);
                        for (Asteroid a : alst) {
                            if (a.before) {
                                if (current == null) {
                                    current = a;
                                    sRem = s;
                                }
                                else if (current.distance > a.distance) {
                                    current = a;
                                    sRem = s;
                                }
                            }
                        }
                        if (current != null) {
                            alst.remove(current);
                            if (!alst.isEmpty())
                                sRem = null;
                            ++i;
                            if (i == 200) {
                                printAsteroid(current);
                                break;
                            }
                            //printAsteroid(current);
                        }
                    }
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedNegSlopes.remove(sRem);
            sRem = null;


            if (!sortedPosSlopes.isEmpty()) {
                for (Double s : sortedPosSlopes) {
                    current = null;
                    if (!s.equals(Double.POSITIVE_INFINITY)) {
                        List<Asteroid> alst = posSlopes.get(s);
                        for (Asteroid a : alst) {
                            if (!a.before) {
                                if (current == null) {
                                    current = a;
                                    sRem = s;
                                } else if (current.distance > a.distance) {
                                    current = a;
                                    sRem = s;
                                }
                            }
                        }
                        if (current != null) {
                            alst.remove(current);
                            if (!alst.isEmpty())
                                sRem = null;
                            ++i;
                            if (i == 200) {
                                printAsteroid(current);
                                break;
                            }

                            //printAsteroid(current);
                        }
                    }
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedPosSlopes.remove(sRem);
            sRem = null;

            if (sortedNegSlopes.contains(Double.NEGATIVE_INFINITY)) {
                List<Asteroid> alst = negSlopes.get(Double.NEGATIVE_INFINITY);
                for (Asteroid a : alst) {
                    if (current == null)
                        current = a;
                    else if (current.distance > a.distance)
                        current = a;
                }
                alst.remove(current);
                if (alst.isEmpty())
                    sRem = Double.NEGATIVE_INFINITY;
                ++i;
                if (i == 200) {
                    printAsteroid(current);
                    break;
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedNegSlopes.remove(sRem);
            sRem = null;


            if (!sortedNegSlopes.isEmpty()) {
                for (Double s : sortedNegSlopes) {
                    current = null;
                    if (!s.equals(Double.NEGATIVE_INFINITY)) {
                        List<Asteroid> alst = negSlopes.get(s);
                        for (Asteroid a : alst) {
                            if (!a.before) {
                                if (current == null) {
                                    current = a;
                                    sRem = s;
                                } else if (current.distance > a.distance) {
                                    current = a;
                                    sRem = s;
                                }
                            }
                        }
                        if (current != null) {
                            alst.remove(current);
                            if (!alst.isEmpty())
                                sRem = null;
                            ++i;
                            if (i == 200) {
                                printAsteroid(current);
                                break;
                            }
                            //printAsteroid(current);
                        }
                    }
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedNegSlopes.remove(sRem);
            sRem = null;



            if (!sortedPosSlopes.isEmpty()) {
                for (Double s : sortedPosSlopes) {
                    current = null;
                    if (!s.equals(Double.POSITIVE_INFINITY)) {
                        List<Asteroid> alst = posSlopes.get(s);
                        for (Asteroid a : alst) {
                            if (a.before) {
                                if (current == null) {
                                    current = a;
                                    sRem = s;
                                } else if (current.distance > a.distance) {
                                    current = a;
                                    sRem = s;
                                }
                            }
                        }
                        if (current != null) {
                            alst.remove(current);
                            if (!alst.isEmpty())
                                sRem = null;
                            ++i;
                            if (i == 200) {
                                printAsteroid(current);
                                break;
                            }
                            //printAsteroid(current);
                        }
                    }
                }
            }
            //if (current != null) printAsteroid(current);
            current = null;
            if (sRem != null) sortedPosSlopes.remove(sRem);
            sRem = null;

        }
        System.out.println(i);
        //printAsteroids();
    }

    private Map<Double, List<Asteroid>> getPosSlopes() {
        Map<Double, List<Asteroid>> pos = new HashMap<>();
        asteroids.forEach((s, lst) -> {
            if(s > 0.0)
                pos.put(s, lst);
            else if (s.equals(0.0) && station.getKey() > lst.get(0).pos.getKey())
                pos.put(s, lst);
        });
        return pos;
    }

    private Map<Double, List<Asteroid>> getNegSlopes() {
        Map<Double, List<Asteroid>> neg = new HashMap<>();
        asteroids.forEach((s, lst) -> {
            if(s < 0.0)
                neg.put(s, lst);
            else if (s.equals(-0.0) && station.getKey() < lst.get(0).pos.getKey())
                neg.put(s, lst);
        });
        return neg;
    }

    private void createAsteroids() {
        asteroids = new HashMap<>();
        for(int y=0; y<input.length; ++y) {
            for(int x=0; x<input[y].length; ++x) {
                if (input[y][x] == '#' && !(station.getKey().equals(x) && station.getValue().equals(y))) {
                    Day10_2.Asteroid current = new Day10_2.Asteroid();
                    current.pos = new Pair<>(x, y);
                    if (station.getValue() >= y)
                        current.before = true;
                    double slope = calcSlope(station, current.pos);
                    current.distance = calcDistance(station, current.pos);
                    if (!asteroids.containsKey(slope))
                        asteroids.put(slope, new ArrayList<>());

                    asteroids.get(slope).add(current);
                }
            }
        }
    }

    private double calcSlope(Pair<Integer, Integer> a1, Pair<Integer, Integer> a2) {
        return ((double) a1.getValue() - (double) a2.getValue()) / ((double) a1.getKey() - (double) a2.getKey());
    }

    private double calcDistance(Pair<Integer, Integer> a1, Pair<Integer, Integer> a2) {
        return Math.sqrt( Math.pow(a1.getKey() - a2.getKey(), 2) + Math.pow(a1.getValue() - a2.getValue(), 2) );
    }

    private void printAsteroids(Map<Double, List<Asteroid>> slopes) {
        //SortedSet<Double> slopes = new TreeSet<>(asteroids.keySet());

        slopes.forEach((s, lst) -> {
            System.out.println("Slope: " + s);
            lst.forEach(a -> System.out.println("   dist: " + a.distance
                    + " x,y " + a.pos.getKey()
                    + "," + a.pos.getValue()));
        });

        System.out.println("--------------");

        negSlopes.forEach((s, lst) -> System.out.println(s));
        System.out.println("************");
        posSlopes.forEach((s, lst) -> System.out.println(s));
    }

    private void printAsteroid(Asteroid a) {
        System.out.println("x,y " + a.pos.getKey()
                + "," + a.pos.getValue());
    }
}
