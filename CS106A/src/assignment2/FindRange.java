package assignment2;
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largets and smallest numbers.");
		
		int	nbrcount = 0;
		int smallets = 0;
		int largets = 0;
		
		while (true) {
			 int a = readInt("? ");
			 if (a == SENTINEL) break;
			 
			 nbrcount++;
			 
			 if (smallets == 0 || smallets > a) smallets = a;
			 if (largets == 0 || largets < a) largets = a;
		}
		
		if (nbrcount > 0) {
		println("smallest: " + smallets);
		println("largets: " + largets);
		}
		else println("No input provided.");
	}
}