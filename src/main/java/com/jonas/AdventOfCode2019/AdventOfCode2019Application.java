package com.jonas.AdventOfCode2019;

import com.jonas.AdventOfCode2019.Data.*;
import com.jonas.AdventOfCode2019.Day1.Day1;
import com.jonas.AdventOfCode2019.Day10.Day10_2;
import com.jonas.AdventOfCode2019.Day11.Day11;
import com.jonas.AdventOfCode2019.Day12.Day12;
import com.jonas.AdventOfCode2019.Day12.Moon;
import com.jonas.AdventOfCode2019.Day2.Day2;
import com.jonas.AdventOfCode2019.Day3.Day3;
import com.jonas.AdventOfCode2019.Day4.Day4;
import com.jonas.AdventOfCode2019.Day5.Day5;
import com.jonas.AdventOfCode2019.Day6.Day6;
import com.jonas.AdventOfCode2019.Day7.Day7;
import com.jonas.AdventOfCode2019.Day8.Day8;
import com.jonas.AdventOfCode2019.Day9.Day9;
import javafx.util.Pair;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AdventOfCode2019Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AdventOfCode2019Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//runDay1();
		//runDay2();
		//runDay3();
		//runDay4();
        //runDay5();
		//runDay6();
		//runDay7();
		//runDay8();
		//runDay9();
		//runDay10();
		//runDay11();
		//runDay12_1();
		//runDay12_2();
		runDay12_2_All();
	}

	public void runDay1() {
		Day1 day1 = new Day1();
		//day1.count(Arrays.asList(12, 14, 1969, 100756));
		day1.puzzle1(Day1.inputLst);

		day1.puzzle2(Day1.inputLst);
	}

	public void runDay2() {
		Day2 day2 = new Day2();
		int ret = day2.computer(Day2.puzzle1);
		System.out.println(ret);

		for(int i = 1; i<100; ++i) {
			for(int j = 1; j<100; ++j) {
				int[] inp = {1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,6,23,1,23,6,27,1,13,27,31,2,13,31,35,1,5,35,39,2,39,13,43,1,10,43,47,2,13,47,51,1,6,51,55,2,55,13,59,1,59,10,63,1,63,10,67,2,10,67,71,1,6,71,75,1,10,75,79,1,79,9,83,2,83,6,87,2,87,9,91,1,5,91,95,1,6,95,99,1,99,9,103,2,10,103,107,1,107,6,111,2,9,111,115,1,5,115,119,1,10,119,123,1,2,123,127,1,127,6,0,99,2,14,0,0};
				inp[1] = i;
				inp[2] = j;
				ret = day2.computer(inp);
				if (ret == 19690720) {
					int ans = 100 * i + j;
					System.out.println("ans = " + ans + " i = " + Integer.toString(i) + " j = " + Integer.toString(j));
				}
			}
		}
	}

	public void runDay3() {
		Day3 day3 = new Day3();
		//day3.run1(Day3.puzzle1, Day3.puzzle2);
		day3.run2(Day3.puzzle1, Day3.puzzle2);
	}

	public void runDay4() {
		Day4 day4 = new Day4();
		day4.run1(307237, 769058);
		day4.run2(307237, 769058);
	}

	public void runDay5() {
		Day5 day5 = new Day5();
		day5.computer(Day5.inp6);
	}

	public void runDay6() {
	    Day6 day6 = new Day6();
	    //day6.run(Data.day6Inp1);
		day6.run2(DataDay6.PUZZLE);
    }

    public void runDay7() {
		Day7 day7 = new Day7();
		//day7.run1();
		day7.run2();
		//day7.runTests();
	}

	public void runDay8() {
		//Day8 day8 = new Day8(2, 2);
		//day8.run1(DataDay8.INP1);
		//Day8 day8 = new Day8(6, 25);
		//day8.run1(DataDay8.PUZZLE);

		//Day8 day8 = new Day8(2, 2);
		//day8.run2(DataDay8.INP2);
		Day8 day8 = new Day8(6, 25);
		day8.run2(DataDay8.PUZZLE);
	}

	public void runDay9() {
		Day9 day9 = new Day9();
		/*
		day9.run1(DataDay9.INP1); System.out.println("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99");
		day9.run1(DataDay9.INP3); System.out.println("1125899906842624");
		day9.run1(DataDay9.INP4); System.out.println("-1");
		day9.run1(DataDay9.INP5); System.out.println("1");
		day9.run1(DataDay9.INP6); System.out.println("109");
		day9.run1(DataDay9.INP7); System.out.println("204");
		day9.run1(DataDay9.INP8); System.out.println("204");
		day9.run1(DataDay9.INP9); System.out.println("204");
		day9.run1(DataDay9.INP10); System.out.println("1");
		day9.run1(DataDay9.INP11); System.out.println("1"); */
		day9.run1(DataDay9.PUZZLE);
	}

	public void runDay10() {
		//Day10 day10 = new Day10();
		//day10.run1(DataDay10.PUZZLE);
		Day10_2 day10_2 = new Day10_2();
		day10_2.run(DataDay10.PUZZLE, new Pair<>(37, 25));
	}

	public void runDay11(){
		Day11 day11 = new Day11();
		day11.run(DataDay11.PUZZLE);
	}

	public void runDay12_1() {
		Day12 day12 = new Day12();
		List<Moon> moons = day12.readData(DataDay12.PUZZLE1);

		for(int i = 1; i < 1001; ++i) {
			day12.motion(moons);
		}

		int totalSystemEnergy = 0;
		for(Moon m : moons) {
			totalSystemEnergy += m.calcTotalEnergy();
		}
		System.out.println(totalSystemEnergy);
	}

	public void runDay12_2() {
		Day12 day12 = new Day12();
		List<Moon> moons = day12.readData(DataDay12.PUZZLE1);
		List<Moon> original = day12.readData(DataDay12.PUZZLE1);

		long step = 1;
		do {
			day12.motion(moons);
			++step;
			if (step % 100000000 == 0)
				System.out.println("Steps: " + step);

		} while(!(original.get(0).equals(moons.get(0))
				&& original.get(1).equals(moons.get(1))
				&& original.get(2).equals(moons.get(2))
				&& original.get(3).equals(moons.get(3))));

		System.out.println("Steps: " + step);
	}

	public void runDay12_2_All() {
		long stepX = runDay12_2_X();
		long stepY = runDay12_2_Y();
		long stepZ = runDay12_2_Z();

		long lcm = lcm(stepX, stepY);
		System.out.println("lcm(x, y) : " + lcm);
		lcm = lcm(lcm, stepZ);
		System.out.println("lcm : " + lcm);
	}

	public long runDay12_2_X() {
		Day12 day12 = new Day12();
		List<Moon> moons = day12.readData(DataDay12.PUZZLE1);
		List<Moon> original = day12.readData(DataDay12.PUZZLE1);

		long step = 1;
		do {
			day12.motionX(moons);
			++step;
			if (step % 100000000 == 0)
				System.out.println("Steps: " + step);

		} while(!(original.get(0).equals(moons.get(0))
				&& original.get(1).equals(moons.get(1))
				&& original.get(2).equals(moons.get(2))
				&& original.get(3).equals(moons.get(3))));

		System.out.println("Steps: " + step);
		return step;
	}

	public long runDay12_2_Y() {
		Day12 day12 = new Day12();
		List<Moon> moons = day12.readData(DataDay12.PUZZLE1);
		List<Moon> original = day12.readData(DataDay12.PUZZLE1);

		long step = 1;
		do {
			day12.motionY(moons);
			++step;
			if (step % 100000000 == 0)
				System.out.println("Steps: " + step);

		} while(!(original.get(0).equals(moons.get(0))
				&& original.get(1).equals(moons.get(1))
				&& original.get(2).equals(moons.get(2))
				&& original.get(3).equals(moons.get(3))));

		System.out.println("Steps: " + step);
		return step;
	}

	public long runDay12_2_Z() {
		Day12 day12 = new Day12();
		List<Moon> moons = day12.readData(DataDay12.PUZZLE1);
		List<Moon> original = day12.readData(DataDay12.PUZZLE1);

		long step = 1;
		do {
			day12.motionZ(moons);
			++step;
			if (step % 100000000 == 0)
				System.out.println("Steps: " + step);

		} while(!(original.get(0).equals(moons.get(0))
				&& original.get(1).equals(moons.get(1))
				&& original.get(2).equals(moons.get(2))
				&& original.get(3).equals(moons.get(3))));

		System.out.println("Steps: " + step);
		return step;
	}

	public static long lcm(long number1, long number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		long absNumber1 = Math.abs(number1);
		long absNumber2 = Math.abs(number2);
		long absHigherNumber = Math.max(absNumber1, absNumber2);
		long absLowerNumber = Math.min(absNumber1, absNumber2);
		long lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}
}
