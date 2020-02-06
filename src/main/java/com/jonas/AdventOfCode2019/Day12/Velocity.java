package com.jonas.AdventOfCode2019.Day12;

public class Velocity {
    private int x, y, z;

    /*** Constructors ***/
    public Velocity() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Velocity(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*** Getters & Setters ***/
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
