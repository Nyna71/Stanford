package assignments.assignment3;

/**
 * Calculates the Greatest Common Divisor between 2 integers, using a recursive implementation of Euclid's Algorihtm.
 * @author Jonathan Puviland
 *
 */
public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println(greatestCommonDivisor(12, 120));

	}

	private static int greatestCommonDivisor(int x, int y) {
		if(x % y == 0) return y;
		else return greatestCommonDivisor(y, x % y);
	}
}