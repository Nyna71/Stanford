package assignment2;
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a number: ");
		int nbrSteps = 0;
		
		while (n != 1) {
			if (n % 2 != 0) {
				println(n + " is odd, so I make 3n+1: " + (3*n+1));
				n = 3*n + 1;
			} else {
				println(n + " is even, so I take half:: " + n/2);
				n /= 2;
			}
			nbrSteps++;
		}
		println("The process took " + nbrSteps + " to reach 1.");
	}
}

