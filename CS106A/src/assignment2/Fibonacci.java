package assignment2;
import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	
	public void run () {
		int maxNbr = readInt("Enter a number for Fibonacci sequence ");
		
		int i = 0;
		int j = 1;
		int k;
		
		println(i);
		println(j);
		while (i+j <= maxNbr) {
			k = i;
			i = j;
			j = k +i;
			println(j);
		}
	}
}
