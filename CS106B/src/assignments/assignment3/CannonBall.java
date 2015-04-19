package assignments.assignment3;

/**
 * Simple recursion example counting number of bullets cannons stacked in pyramids.
 * @author id922010
 *
 */
public class CannonBall {

	public static void main(String[] args) {
		System.out.println("Number of bullets: " + cannonBall(3));

	}

	private static int cannonBall(int height) {
		if(height == 0) return 0;
		else return height * height + cannonBall(height - 1);
	}

}
