
import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class BoardCell extends GCompound {

	public BoardCell(int color, int size) {
		cellColor = new Color(color);
		cellSize = size;
		cellRect = new GRect(cellSize, cellSize);
		this.setCellColor(cellColor);
		this.add(cellRect);
	}
	
	public BoardCell(int color, int size, int checker) {
		this(color, size);
		putChecker(checker);
	}
	
	public void putChecker(int color) {
		checkerColor = new Color(color);
		cellChecker = new GOval(5, 5, cellSize - 10, cellSize - 10);
		cellChecker.setFillColor(checkerColor);
		cellChecker.setFilled(true);
		this.add(cellChecker);
		hasChecker = true;
	}
	
	public void removeChecker() {
		checkerColor = null;
		remove(cellChecker);
		hasChecker = false;
	}
	
	public boolean hasCheker() {
		return hasChecker;
	}
	
	public Color getChekerColor() {
		if(hasChecker) return checkerColor;
		else return null;
	}
	
	public Color getCellColor() {
		return cellColor;
	}
	
	public void setCellColor(Color cell) {
		cellColor = cell;
		cellRect.setFillColor(cellColor);
		cellRect.setFilled(true);
	}
	
	public void setCellLocation(int xPos, int yPos) {
		this.setLocation(xPos, yPos);
	}
	
	private int cellSize;
	private Color cellColor;
	private Color checkerColor;
	private GRect cellRect;
	private GOval cellChecker;
	private boolean hasChecker = false;
}
