
import java.awt.Color;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.*;

public class BookExample extends GraphicsProgram {
	
	private static final int SQUARE_SIZE = 40;
	
	public void run() {

		initCheckerboard(board);
		drawCheckerboard(board);
		
		addMouseListeners();
	}

	public void mouseClicked(MouseEvent e) {
		int row = e.getY() / SQUARE_SIZE;
		int col = e.getX() / SQUARE_SIZE;
		
		if(board[row][col].hasCheker())
			board[row][col].removeChecker();
		else
			board[row][col].putChecker(Color.YELLOW.getRGB());
		
		removeAll();
		drawCheckerboard(board);
	}
	
	private void drawCheckerboard(BoardCell[][] board) {
		// Draw board
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++) {
				board[row][col].setCellLocation(col * SQUARE_SIZE, row * SQUARE_SIZE);
				add(board[row][col]);
			}
	}

	private void initCheckerboard(BoardCell[][] board) {
		// Initialize Cell Colors
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++) {
				// Is this an odd cell ?
				if((row + col) % 2 == 1)
					board[row][col] = new BoardCell(Color.GRAY.getRGB(), SQUARE_SIZE);
				else
					board[row][col] = new BoardCell(Color.WHITE.getRGB(), SQUARE_SIZE);
			}

		// Initialize Black Checkers
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 8; col++)
				// Put Black Checkers on gray cells
				if(board[row][col].getCellColor().equals(Color.GRAY))
					board[row][col].putChecker(Color.BLACK.getRGB());
		
		// Initialize Red Checkers
				for(int row = 5; row < 8; row++)
					for(int col = 0; col < 8; col++)
						// Put Red Checkers on gray cells
						if(board[row][col].getCellColor().equals(Color.GRAY))
							board[row][col].putChecker(Color.RED.getRGB());
	}

	private BoardCell[][] board = new BoardCell[8][8];
}
