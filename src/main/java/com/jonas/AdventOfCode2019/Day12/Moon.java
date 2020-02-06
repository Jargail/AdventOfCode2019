package com.jonas.AdventOfCode2019.Day12;

public class Moon {
    private Position position;
    private Velocity velocity;

    public Moon(Position position, Velocity velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void applyGravity(Moon moon) {
        applyGravityX(moon);

        applyGravityY(moon);

        applyGravityZ(moon);
    }

    public void applyGravityZ(Moon moon) {
        if (this.position.getZ() < moon.position.getZ())
            this.velocity.setZ(this.velocity.getZ() + 1);
        else if (this.position.getZ() > moon.position.getZ())
            this.velocity.setZ(this.velocity.getZ() - 1);
    }

    public void applyGravityY(Moon moon) {
        if (this.position.getY() < moon.position.getY())
            this.velocity.setY(this.velocity.getY() + 1);
        else if (this.position.getY() > moon.position.getY())
            this.velocity.setY(this.velocity.getY() - 1);
    }

    public void applyGravityX(Moon moon) {
        if (this.position.getX() < moon.position.getX())
            this.velocity.setX(this.velocity.getX() + 1);
        else if (this.position.getX() > moon.position.getX())
            this.velocity.setX(this.velocity.getX() - 1);
    }

    public void applyVelocity() {
        applyVelocityX();
        applyVelocityY();
        applyVelocityZ();
    }

    public void applyVelocityZ() {
        position.setZ(position.getZ() + velocity.getZ());
    }

    public void applyVelocityY() {
        position.setY(position.getY() + velocity.getY());
    }

    public void applyVelocityX() {
        position.setX(position.getX() + velocity.getX());
    }

    public int calcPotentialEnergy() {
        return Math.abs(position.getX()) + Math.abs(position.getY()) + Math.abs(position.getZ());
    }

    public int calcKineticEnergy() {
        return Math.abs(velocity.getX()) + Math.abs(velocity.getY()) + Math.abs(velocity.getZ());
    }

    public int calcTotalEnergy() {
        return calcPotentialEnergy() * calcKineticEnergy();
    }

    public void printCoordinates() {
        String format = String.format("pos=<x=%d, y=%d, z=%d>, vel=<x=%d, y=%d, z=%d>",
                position.getX(), position.getY(), position.getZ(),
                velocity.getX(), velocity.getY(), velocity.getZ());
        System.out.println(format);
    }

    public void printEnergies() {
        String msg = String.format("pot: %d \t kin: %d \t tot: %d",
                calcPotentialEnergy(),
                calcKineticEnergy(),
                calcTotalEnergy());
        System.out.println(msg);
    }

    public boolean equals(Moon other) {
        return position.getX() == other.getPosition().getX()
                && position.getY() == other.getPosition().getY()
                && position.getZ() == other.getPosition().getZ();
    }

    /*** Getters & Setters ***/
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
