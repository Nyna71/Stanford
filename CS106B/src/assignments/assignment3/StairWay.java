package assignments.assignment3;

import java.util.Stack;

public class StairWay {

	private static final int SMALL_STEP = 2;
	private static final int LARGE_STEP = 3;

	public static void main(String[] args) {
		Stack<Integer> path = new Stack<Integer>();
		CountWays(8, path);
	}

	private static void CountWays(int reaminingStairs, Stack<Integer> path) {
		if(reaminingStairs == 0) {
			System.out.println(path);
			path.pop();
			return;
		}

		if(reaminingStairs < 0) {
			path.pop();
			return;
		}

		// Try with small step
		path.push(SMALL_STEP);
		CountWays(reaminingStairs - SMALL_STEP, path);

		// Try with large step
		path.push(LARGE_STEP);
		CountWays(reaminingStairs - LARGE_STEP, path);

		if(!path.isEmpty()) path.pop();
	}

}
