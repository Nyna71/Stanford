package assignments.assignment2;

public class MineSweeper {

	private static final int[][] MINES = new int[][] {
		{1, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0, 1},
		{1, 1, 0, 1, 0, 1},
		{1, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0}
	};

	public static void main(String[] args) {
		int[][] mineCount = new int[6][6];
		mineCount = countMines();

		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(mineCount[i][j] + "\t");
			}
		System.out.println();
		}
	}

	private static int[][] countMines() {
		int[][] mineCount = new int[6][6];
		int[][] minesTemps = new int[8][8];

		initialize(minesTemps);

		for(int i = 1; i < 7; i++) {
			for(int j = 1; j < 7; j++) {
				// Add current row
				mineCount[i-1][j-1] = minesTemps[i][j-1] + minesTemps[i][j] + minesTemps[i][j+1];

				// Add previous row
				mineCount[i-1][j-1] += minesTemps[i-1][j-1] + minesTemps[i-1][j] + minesTemps[i-1][j+1];

				// Add new row
				mineCount[i-1][j-1] += minesTemps[i+1][j-1] + minesTemps[i+1][j] + minesTemps[i+1][j+1];
			}
		}


		return mineCount;
	}

	private static void initialize(int[][] minesTemps) {
		//Initialise first and last row with 0
		for(int j = 0; j < 8; j++) {
			minesTemps[0][j] = 0;
			minesTemps[7][j] = 0;
		}

		//Initialise first and last columns with 0
		for(int i = 0; i < 8; i++) {
			minesTemps[i][0] = 0;
			minesTemps[i][7] = 0;
		}

		//Copy mines value to tempMines
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				minesTemps[i+1][j+1] = MINES[i][j];
			}
	}
}
