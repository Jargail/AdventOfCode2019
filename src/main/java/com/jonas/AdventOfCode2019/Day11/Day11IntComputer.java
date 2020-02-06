package com.jonas.AdventOfCode2019.Day11;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day11IntComputer {
    int instrPointer = 0;
    boolean break99 = false;
    long[] input;
    long nrInput = 0, relBase = 0;
    boolean firstInput = true;

    Pair<Integer, Integer> currentPos = new Pair<>(0,0);
    Direction currentDirection = Direction.UP;
    Color currentColor = Color.WHITE;
    List<Point> path = new LinkedList<>();

    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    enum Color {
        BLACK,
        WHITE
    }

    public Day11IntComputer(long[] input) {
        this.input = input;
    }

    public long comput() {

        long instruction;
        long val1, val2, val3;
        long param1, param2, param3, ret = 0;

        Boolean cont = true;
        while(instrPointer < input.length && cont) {
            instruction = input[instrPointer++];
            Day11IntComputer.Instruction instr = new Day11IntComputer.Instruction(instruction);

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
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    param1 = input[instrPointer++];
                    try {
                        //int cmdIn = Integer.parseInt(reader.readLine());
                        //long cmdIn = Long.parseLong(reader.readLine());
                        int cmdIn;
                        if (firstInput) {
                            cmdIn = 1;
                            firstInput = false;
                        } else
                            cmdIn = getColorIntvalue();

                        setValue(cmdIn, relBase, param1, instr.paramMode1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    param1 = input[instrPointer++];
                    val1 = getValue(relBase, param1, instr.paramMode1);
                    //ret = val1;
                    //cont = false;
                    if (nrInput == 0) {
                        currentColor = val1 == 0 ? Color.BLACK : Color.WHITE;
                        addColor();
                    }
                    else {
                        turn((int) val1);
                        moveRobot();
                    }

                    nrInput = ++nrInput % 2;
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

        printPath();
        System.out.println("Antal rutor: " + path.size());
        System.out.println("--- Part two ---");
        printPartTwo();

        return ret;
    }

    private void printPartTwo() {
        Pair<Integer, Integer> xMinMax = getXMinMax();
        Pair<Integer, Integer> yMinMax = getYMinMax();
        Integer xMin = xMinMax.getKey(), xMax = xMinMax.getValue(),
                yMin = yMinMax.getKey(), yMax = yMinMax.getValue();


        String msg = String.format("xMin: %d, xMax: %d", xMin, xMax);
        System.out.println(msg);

        msg  = String.format("yMin: %d, yMax: %d", yMin, yMax);
        System.out.println(msg);

        for (int i = yMax; i >= yMin; --i) {
            ArrayList<Character> row = new ArrayList<>();
            String strRow = "";
            for (int j = xMin; j <= xMax; ++j) {
                Point p = new Point(j, i, Color.BLACK);
                if (path.contains(p)) {
                    p = path.get(path.indexOf(p));
                    row.add(p.color == Color.BLACK ? ' ' : '#');
                    strRow += (p.color == Color.BLACK ? " " : '#');
                } else {
                    row.add(' ');
                    strRow += " ";
                }

            }
            //System.out.println(row.toString());
            System.out.println(strRow);
        }
    }

    private Pair<Integer, Integer> getXMinMax() {
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        for (Point p : path) {
            min = Integer.min(min, p.x);
            max = Integer.max(max, p.x);
        }
        return new Pair<>(min, max);
    }

    private Pair<Integer, Integer> getYMinMax() {
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        for (Point p : path) {
            min = Integer.min(min, p.y);
            max = Integer.max(max, p.y);
        }
        return new Pair<>(min, max);
    }

    private int getColorIntvalue() {
        Point p = new Point(currentPos.getKey().intValue(), currentPos.getValue().intValue(), Color.BLACK);
        if (path.contains(p))
            return path.get(path.indexOf(p)).color == Color.BLACK ? 0 : 1;
        else
            return 0;
    }

    private void printPath() {
        path.stream().forEach(pos ->
                System.out.println("(" + pos.x + "," + pos.y + ")" + " " + pos.color.toString()));
    }

    private void moveRobot() {
        switch (currentDirection) {
            case RIGHT:
                currentPos = new Pair<>(currentPos.getKey()+1, currentPos.getValue());
                break;
            case LEFT:
                currentPos = new Pair<>(currentPos.getKey()-1, currentPos.getValue());
                break;
            case UP:
                currentPos = new Pair<>(currentPos.getKey(), currentPos.getValue()+1);
                break;
            case DOWN:
                currentPos = new Pair<>(currentPos.getKey(), currentPos.getValue()-1);
                break;
        }
    }

    private void addColor() {
        Point p = new Point(currentPos.getKey().intValue(), currentPos.getValue().intValue(), currentColor);

        if (path.contains(p)) {
            path.remove(p);
            path.add(p);
        }
        else
            path.add(p);
    }

    private void turn(int inp) {
        switch (inp) {
            case 0: {
                switch (currentDirection) {
                    case UP:
                        currentDirection = Direction.LEFT;
                        break;
                    case LEFT:
                        currentDirection = Direction.DOWN;
                        break;
                    case DOWN:
                        currentDirection = Direction.RIGHT;
                        break;
                    case RIGHT:
                        currentDirection = Direction.UP;
                        break;
                }
                break;
            }
            case 1: {
                switch (currentDirection) {
                    case UP:
                        currentDirection = Direction.RIGHT;
                        break;
                    case LEFT:
                        currentDirection = Direction.UP;
                        break;
                    case DOWN:
                        currentDirection = Direction.LEFT;
                        break;
                    case RIGHT:
                        currentDirection = Direction.DOWN;
                        break;
                }
                break;
            }
        }
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

    private final class Point {
        int x;
        int y;
        Color color;

        Point(int x, int y, Color c) {
            this.x = x;
            this.y = y;
            this.color = c;
        }

        @Override
        public boolean equals(Object o) {
            //if (this.equals(o))
            //    return true;

            Point other = (Point) o;
            if (other.x == this.x
                    && other.y == this.y)
                return true;

            return false;
        }
    }
}
