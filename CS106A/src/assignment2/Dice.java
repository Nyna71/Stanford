package assignment2;
import acm.program.*;
import acm.util.*;

public class Dice extends ConsoleProgram {

	private static final int DICE_FACES = 6;
	
	public void run() {
		rGen.setSeed(1);
		
		int nbrDices = readInt("Enter number of dices: ");
		int dice = 0;
		int totalDices = 0;
		
		while (totalDices < nbrDices * DICE_FACES) {
			totalDices = 0;
			for (int i=0; i<nbrDices; i++) {
				dice = rGen.nextInt(1,6);
				println("Dice n°" + i + "=" + dice);
				totalDices += dice;
			}
			println ("Total dices = " + totalDices);
			println ("Not enough, we continue...");
			println();
		}
		println ("Yeah we got it !");
	}
	
	private RandomGenerator rGen = new RandomGenerator();
}
