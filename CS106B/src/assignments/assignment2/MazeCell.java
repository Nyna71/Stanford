package assignments.assignment2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MazeCell extends JComponent {

	private static final long serialVersionUID = 1L;

	public static final int CELL_SIZE = 30;
	public static final int OFFSET = CELL_SIZE / 5;
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	public static final int WEST = 4;

	boolean isNorthClean;
	boolean isSouthClean;
	boolean isWestClean;
	boolean isEastClean;
	boolean isExcluded;
	boolean isChecked;

	private int xCoord;
	private int yCoord;

	private int rowIndex;
	private int colIndex;

	private Color checkColor;

	public MazeCell(int x, int y, int row, int col) {
		isNorthClean = false;
		isSouthClean = false;
		isWestClean = false;
		isEastClean = false;
		isExcluded = true;
		isChecked = false;

		this.xCoord = x;
		this.yCoord = y;

		rowIndex = row;
		colIndex = col;

		checkColor = Color.BLUE;
	}

	public void setNonExcluded() {
		isExcluded = false;
	}

	public void checkCell() {
		isChecked = true;
	}

	public void uncheckCell() {
		isChecked = false;
	}

	public void changeCheckStatus() {
		isChecked = !isChecked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public boolean isExcluded() {
		return isExcluded;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void removeBorder(int border) {
		if(border == NORTH)
			isNorthClean = true;

		if(border == SOUTH)
			isSouthClean = true;

		if(border == EAST)
			isEastClean = true;

		if(border == WEST)
			isWestClean = true;
	}

	public void setCheckColor(Color color) {
		checkColor = color;
	}

	/**
	 * Displays the MazeCell object by drawing line borders and checks (crosses) where applicable.
	 */
	protected void paintComponent(Graphics g) {
		// Draw a black border in each directions where applicable
		g.setColor(Color.BLACK);
		if(!isNorthClean)
			g.drawLine(xCoord, yCoord, xCoord + CELL_SIZE, yCoord);
		if(!isSouthClean)
			g.drawLine(xCoord, yCoord + CELL_SIZE, xCoord + CELL_SIZE, yCoord + CELL_SIZE);
		if(!isWestClean)
			g.drawLine(xCoord, yCoord, xCoord, yCoord + CELL_SIZE);
		if(!isEastClean)
			g.drawLine(xCoord + CELL_SIZE, yCoord, xCoord + CELL_SIZE, yCoord + CELL_SIZE);

		// If cell is checked, draw a a cross in the cell using the provided check color (default blue).
		if(isChecked) {
			g.setColor(checkColor);
			g.drawLine(xCoord + OFFSET, yCoord + OFFSET, xCoord + CELL_SIZE - OFFSET, yCoord + CELL_SIZE - OFFSET);
			g.drawLine(xCoord + CELL_SIZE - OFFSET, yCoord + OFFSET, xCoord + OFFSET, yCoord + CELL_SIZE - OFFSET);
		}
		// If cell is unchecked, draw a white rectangle in the cell.
		else {
			g.setColor(Color.WHITE);
			g.fillRect(xCoord + 1, yCoord + 1, CELL_SIZE - 2, CELL_SIZE - 2);

		}
	}
}
