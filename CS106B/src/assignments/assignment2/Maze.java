package assignments.assignment2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Creates a Maze object whose dimensions are specified by the width and height parameters.
 * @author Jonathan Puvilland
 *
 */
public class Maze {

	private MazeCell[][] mazeCells;
	private int mazeWidth;
	private int mazeHeight;

	public Maze(int width, int height) {
		mazeHeight = height;
		mazeWidth = width;
		mazeCells = new MazeCell[mazeHeight][mazeWidth];
		initialiseComponents();
	}

	/**
	 * @return The width (number of columns) of the maze.
	 */
	public int getMazeWidth() {
		return mazeWidth;
	}

	/**
	 * @return The height (number of rows) of the maze.
	 */
	public int getMazeHeight() {
		return mazeHeight;
	}

	/**
	 * Initialise the maze with JComponent objects called <i>MazeCells</i>. Those JComponenents are used to draw the maze on a MazePanel.
	 */
	private void initialiseComponents() {
		for(int i = 0; i < mazeHeight; i++)
			for(int j = 0; j < mazeWidth; j++) {
				mazeCells[i][j] = new MazeCell(j * MazeCell.CELL_SIZE, i * MazeCell.CELL_SIZE, i, j);
			}
	}

	/**
	 * Returns the <i>MazeCell</i> object present in the maze at the location specified by the row and column parameters.
	 * @param row The row in the maze
	 * @param col The column in the maze
	 * @return
	 */
	public MazeCell getMazeCell(int row, int col) {
		return mazeCells[row][col];
	}

	/**
	 * Returns the <i>MazeCell</i> object present in the maze at the location specified by <i>Cell Location</i> object.
	 * @param row The row in the maze
	 * @param col The column in the maze
	 * @return
	 */
	public MazeCell getMazeCell(CellLocation location) {
		return mazeCells[location.rowIndex][location.colIndex];
	}

	/**
	 * Creates a perfect maze using the Aldous and Broder algorithm.
	 */
	public void initiliaseMaze() {
		//1° Get a random cell and mark it as included
		Random rnd = new Random();
		MazeCell currentCell = mazeCells[rnd.nextInt(mazeHeight)][rnd.nextInt(mazeWidth)];
		currentCell.setNonExcluded();
		int nbrNonExcluded = 1;

		//2° Repeat until all cells are marked as included
		while(nbrNonExcluded < mazeHeight * mazeWidth) {
			//3° Choose a random neighbour cell
			int direction = rnd.nextInt(4) + 1;
			MazeCell nextCell = getNextCell(currentCell, direction);

			//4° If neighbour is excluded, remove walls between current cell and the random neighbour and mar the neighbour as included.
			if(nextCell.isExcluded()) {
				currentCell.removeBorder(direction);
				nextCell.removeBorder(getOppositeDirection(direction));
				nextCell.setNonExcluded();
				nbrNonExcluded++;
			}

			//5° The random neighbour cell becomes the current cell.
			currentCell = nextCell;
		}
		//maze.getMazeCell(MazeFrame.MAZE_HEIGHT - 1, 0).checkCell();
		//maze.getMazeCell(0, MazeFrame.MAZE_WIDTH - 1).checkCell();
	}

	/**
	 * Retrieves the neighbour MazeCell object specified by the current cell and the direction (North, South, etc)
	 * If no valid neighbour cell can be found, returns the current cell.
	 * @param currentCell The current cell
	 * @param direction The random direction to go from the current cell.
	 * @return The neighbour cell in the specified direction if valid, the current if not valid
	 * (for example when neighbour cell is out of bound).
	 */
	private MazeCell getNextCell(MazeCell currentCell, int direction) {
		MazeCell nextCell = currentCell;
		if(direction == MazeCell.NORTH && currentCell.getRowIndex() != 0) {
			// System.out.println("Current Cell: " + currentCell.getRowIndex() + "\t" + currentCell.getColIndex());
			nextCell = mazeCells[currentCell.getRowIndex() - 1][currentCell.getColIndex()];
		}

		if(direction == MazeCell.SOUTH && currentCell.getRowIndex() < mazeHeight - 1) {
			// System.out.println("Current Cell: " + currentCell.getRowIndex() + "\t" + currentCell.getColIndex());
			nextCell = mazeCells[currentCell.getRowIndex() + 1][currentCell.getColIndex()];
		}

		if(direction == MazeCell.EAST && currentCell.getColIndex() < mazeWidth - 1) {
			// System.out.println("Current Cell: " + currentCell.getRowIndex() + "\t" + currentCell.getColIndex());
			nextCell = mazeCells[currentCell.getRowIndex()][currentCell.getColIndex() + 1];

		}

		if(direction == MazeCell.WEST && currentCell.getColIndex() != 0) {
			// System.out.println("Current Cell: " + currentCell.getRowIndex() + "\t" + currentCell.getColIndex());
			nextCell = mazeCells[currentCell.getRowIndex()][currentCell.getColIndex() - 1];
		}

		return nextCell;
	}

	/**
	 * Calculates the polar opposite direction. Ex. North opposite direction is South.
	 * @param direction The original direction
	 * @return The opposite direction.
	 */
	private int getOppositeDirection(int direction) {
		if(direction == MazeCell.NORTH)
			return MazeCell.SOUTH;

		else if(direction == MazeCell.SOUTH)
			return MazeCell.NORTH;

		else if(direction == MazeCell.EAST)
			return MazeCell.WEST;

		else
			return MazeCell.EAST;
	}

	/**
	 * Resolves a maze using the first breadth-first search algorithm
	 * @param maze A maze frame object containing an initialised maze
	 * @return
	 */
	public Stack<CellLocation> solveMaze() {
		CellLocation currentLocation = new CellLocation(mazeHeight - 1, 0); //Bottom Left
		CellLocation exitLocation = new CellLocation(0, mazeWidth - 1); //Upper Right

		//Create Queue of paths. A Path is a collection of cell locations. Cell locations in a path are organized in a stack
		//for getting easy access to the last cell location of the path
		Queue<Stack<CellLocation>> paths = new LinkedList<Stack<CellLocation>>();
		Stack<CellLocation> currentPath = new Stack<CellLocation>();

		//Initialise a 1-step path with the current location
		currentPath.add(currentLocation);
		paths.add(currentPath);

		while(true) {
			// Dequeue shortest path
			currentPath = paths.poll();
			if(currentPath.peek().equals(exitLocation)) {
				System.out.println("Exit found!");
				return(currentPath);
			}

			MazeCell cell = this.getMazeCell(currentPath.peek());

			// Enqueue new path to the North
			if(cell.isNorthClean) {
				// Create a new path, copy current path
				Stack<CellLocation> newPath = new  Stack<CellLocation>();
				newPath.addAll(currentPath);

				CellLocation newLocation = new CellLocation(newPath.peek().rowIndex - 1, newPath.peek().colIndex);
				addNewPath(paths, newPath, newLocation);
			}

			// Enqueue new path to the South
			if(cell.isSouthClean) {
				// Create a new path, copy current path
				Stack<CellLocation> newPath = new  Stack<CellLocation>();
				newPath.addAll(currentPath);

				CellLocation newLocation = new CellLocation(newPath.peek().rowIndex + 1, newPath.peek().colIndex);
				addNewPath(paths, newPath, newLocation);
			}

			// Enqueue new path to the East
			if(cell.isEastClean) {
				// Create a new path, copy current path
				Stack<CellLocation> newPath = new  Stack<CellLocation>();
				newPath.addAll(currentPath);

				CellLocation newLocation = new CellLocation(newPath.peek().rowIndex, newPath.peek().colIndex + 1);
				addNewPath(paths, newPath, newLocation);
			}

			// Enqueue new path to the West
			if(cell.isWestClean) {
				// Create a new path, copy current path
				Stack<CellLocation> newPath = new  Stack<CellLocation>();
				newPath.addAll(currentPath);

				CellLocation newLocation = new CellLocation(newPath.peek().rowIndex, newPath.peek().colIndex - 1);
				addNewPath(paths, newPath, newLocation);
			}
		}
	}

	/**
	 * Add a new path to the list (Queue) of paths to go through in order resolve the maze. The method verifies first that the
	 * new location has not been visited yet in the path. This test is required to avoid going back on locations already visited
	 * by the path and creating a loop.
	 * @param paths The list (Queue) of paths to go through.
	 * @param newPath The new path to add to the queue
	 * @param newLocation The new location to add to the new path
	 */
	private void addNewPath(Queue<Stack<CellLocation>> paths, Stack<CellLocation> newPath, CellLocation newLocation) {
		if(!newPath.contains(newLocation)) {
			newPath.add(newLocation);
			paths.add(newPath);
		}
	}
}
