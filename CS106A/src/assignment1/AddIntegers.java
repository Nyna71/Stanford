package assignment1;
import acm.program.*; 

public class AddIntegers extends ConsoleProgram {
	public void run () {
		println("This Program adds two numbers");
		int n1 = readInt ("Enter Number 1: ");
		int n2 = readInt ("Enter Number 2: ");
		int Total = n1 + n2;
		
		println("Total = " + Total + ";");
	}
}
