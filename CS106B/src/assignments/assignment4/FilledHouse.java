package assignments.assignment4;

public class FilledHouse {

	private static char[][] house = new char[10][13];
	
	static class Point {
		int row, col;
		
		Point (int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) {
		init();
		for(int i = 0; i < 10; i++)
			System.out.println(house[i]);
		
		System.out.println("\n------------- Coloring House -------------\n");

		Point p = new Point(3, 5);
		fillRegion(p);
		
		for(int i = 0; i < 10; i++)
			System.out.println(house[i]);
	}

	private static void fillRegion(Point p) {
		if(house[p.row][p.col] != ' ')
			return;
		
		house[p.row][p.col] = '-';
		
		// Fill up
		if(p.row != 0)
			fillRegion(new Point(p.row - 1, p.col));
		
		// Fill Down
		if(p.row != 9)
			fillRegion(new Point(p.row + 1, p.col));
		
		// Fill Left
		if(p.col != 0)
			fillRegion(new Point(p.row, p.col - 1));

		// Fill Right
		if(p.col != 12)
			fillRegion(new Point(p.row, p.col + 1));	
	}

	private static void init() {
		house[0] = "     XXX     ".toCharArray();
		house[1] = "    X   X    ".toCharArray();
		house[2] = "   X     X   ".toCharArray();
		house[3] = "  X       X  ".toCharArray();
		house[4] = " X         X ".toCharArray();
		house[5] = "X           X".toCharArray();
		house[6] = "X   XXXXX   X".toCharArray();
		house[7] = "X   X   X   X".toCharArray();
		house[8] = "X   X   X   X".toCharArray();
		house[9] = "XXXXXXXXXXXXX".toCharArray();
	}

}
