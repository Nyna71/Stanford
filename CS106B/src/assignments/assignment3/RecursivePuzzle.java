package assignments.assignment3;

import java.util.ArrayList;
import java.util.List;

class RecursivePuzzle {

	public static void main(String[] args) {
		List<PuzzleCell> puzzleCells = new ArrayList<>();
		init(puzzleCells);
		System.out.println("Puzzle configuration: " + puzzleCells);
		System.out.println("Puzzle Solved ? " + SolvePuzzle(puzzleCells, 0));
	}

	private static boolean SolvePuzzle(List<PuzzleCell> puzzleCells, int index) {
		boolean solvedLeft = false;
		boolean solvedRight = false;
		
		// Base Case
		if(puzzleCells.get(index).cellValue == 0) {
			//puzzleCells.get(index).isVisited = true;
			System.out.println("Solution found: " + puzzleCells);
			return true;
		}
		
		puzzleCells.get(index).isVisited = true;
		int step = puzzleCells.get(index).cellValue;
		
		//Try to go left
		if(index - step > 0 && !puzzleCells.get(index - step).isVisited)
			solvedLeft = SolvePuzzle(puzzleCells, index - step);
			
		//Try to go right
		if(index + step <= puzzleCells.size() && !puzzleCells.get(index + step).isVisited)
			solvedRight = SolvePuzzle(puzzleCells, index + step);	
		
		//No remaining choices, undo last choice, and return current resolution
		puzzleCells.get(index).isVisited = false;
		return solvedLeft || solvedRight;
	}

	private static void init(List<PuzzleCell> puzzleCells) {
		puzzleCells.add(new PuzzleCell(1));
		puzzleCells.add(new PuzzleCell(3));
		puzzleCells.add(new PuzzleCell(4));
		puzzleCells.add(new PuzzleCell(2));
		puzzleCells.add(new PuzzleCell(1));
		puzzleCells.add(new PuzzleCell(0));
	}
}

class PuzzleCell {
	int cellValue;
	boolean isVisited;
	
	PuzzleCell (int value) {
		this.cellValue = value;
		this.isVisited = false;
	}

	@Override
	public String toString() {
		return String.valueOf("(" + cellValue + ", " + isVisited + ")");
	}
}
