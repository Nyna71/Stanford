package assignment2;
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 40;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 16;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
//	Get window width, in order to calculate center position
		int windowsWidth = getWidth();
		int windowsHeight = getHeight();
		
		printTitle();

//	Outer Loop to go up line by line from bottom to top of Pyramid
		for (int j = 0; j < BRICKS_IN_BASE; j++) {
			int bricksOnLine = BRICKS_IN_BASE - j;
			
			int yPos = windowsHeight - (BRICK_HEIGHT * (j+1));
			int xPos = (windowsWidth - (bricksOnLine * BRICK_WIDTH)) / 2;
			
//	Inner loop to fill-in a single line of bricks
			for (int i = 0; i < BRICKS_IN_BASE - j; i++) {
				add(new GRect(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT));
				xPos += BRICK_WIDTH;
			}
		}	
	}
	
	private void printTitle() {
		int windowsCenter = getWidth() / 2;
		GLabel pyramidTitle = new GLabel ("Center Position of the Windows is " + windowsCenter);
		int titleWidth = (int) pyramidTitle.getWidth();
		add(pyramidTitle, windowsCenter - (titleWidth / 2), 10);
	}
}

