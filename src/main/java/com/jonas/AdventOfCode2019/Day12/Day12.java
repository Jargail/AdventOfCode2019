package com.jonas.AdventOfCode2019.Day12;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public List<Moon> motion(List<Moon> moons) {
        for(Moon m : moons) {
            for (Moon other : moons) {
                if (!m.equals(other))
                    m.applyGravity(other);
            }
        }

        for(Moon m : moons) {
            m.applyVelocity();
        }
        return moons;
    }

    public List<Moon> motionX(List<Moon> moons) {
        for(Moon m : moons) {
            for (Moon other : moons) {
                if (!m.equals(other))
                    m.applyGravityX(other);
            }
        }

        for(Moon m : moons) {
            m.applyVelocityX();
        }
        return moons;
    }

    public List<Moon> motionZ(List<Moon> moons) {
        for(Moon m : moons) {
            for (Moon other : moons) {
                if (!m.equals(other))
                    m.applyGravityZ(other);
            }
        }

        for(Moon m : moons) {
            m.applyVelocityZ();
        }
        return moons;
    }

    public List<Moon> motionY(List<Moon> moons) {
        for(Moon m : moons) {
            for (Moon other : moons) {
                if (!m.equals(other))
                    m.applyGravityY(other);
            }
        }

        for(Moon m : moons) {
            m.applyVelocityY();
        }
        return moons;
    }


    public List<Moon> readData(String[] input) {
        ArrayList<Moon> ret = new ArrayList<>();

        for (String s : input) {
            int eqIndex = 0, commaIndex = 0, leftBracketIndex = 0;
            int x = 0, y = 0, z = 0;
            eqIndex = s.indexOf('=', eqIndex);
            commaIndex = s.indexOf(',', commaIndex);
            x = Integer.valueOf(s.substring(eqIndex+1, commaIndex++));

            eqIndex = s.indexOf('=', commaIndex);
            commaIndex = s.indexOf(',', commaIndex);
            y = Integer.valueOf(s.substring(eqIndex+1, commaIndex++));

            eqIndex = s.indexOf('=', commaIndex);
            leftBracketIndex = s.indexOf('>');
            z = Integer.valueOf(s.substring(eqIndex+1, leftBracketIndex));

            Position position = new Position(x, y, z);
            Moon moon = new Moon(position, new Velocity());
            ret.add(moon);
        }
        return ret;
    }
}
