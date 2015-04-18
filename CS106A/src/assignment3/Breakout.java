package assignment3;
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
	private static final int NO_WALL = 0;
	private static final int EAST = 1;
	private static final int WEST = 2;
	private static final int NORTH = 3;
	private static final int SOUTH = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		int credits = NTURNS;
		int remainingBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		int score = 0;
		
		GLabel scoreLabel = new GLabel ("Score: " + score, 50, 20);
		GLabel creditsLabel = new GLabel ("Credits: " + credits, WIDTH - 100, 20);
		
		scoreLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		creditsLabel.setFont(new Font("Serif", Font.ITALIC, 18));
		
		drawBoard();
		drawPaddle();
		drawBall();
		addMouseListeners();
		
		add(scoreLabel);
		add(creditsLabel);
		
		pause(1000);
		
		while (credits > 0 && remainingBricks > 0) {
			moveBall();
			GObject collider = getCollidingObject();
			
			// Did the ball collide the paddle ?
			if (collider == paddle)	{
				// Did the collision occur inside the paddle ? Reset the ball on top of paddle. 
				if (ball.getY() + BALL_RADIUS*2 > paddle.getY()) ball.setLocation(ball.getX(), paddle.getY() - BALL_RADIUS*2);
				wall = NO_WALL;
				changeBallDirection();
			} else 
			
			// Did the ball collide a brick ?	
			if (collider != null && collider != scoreLabel && collider != creditsLabel) {
				// Increase the score according brick's color
				if (collider.getColor() == Color.CYAN) score++;
				if (collider.getColor() == Color.GREEN) score +=2;
				if (collider.getColor() == Color.YELLOW) score +=3;
				if (collider.getColor() == Color.ORANGE) score +=4;
				if (collider.getColor() == Color.RED) score +=5;
				
				remove(collider);
				remainingBricks--;
				
				// Increase ball speed every 10 bricks
				if (remainingBricks % 12 == 0) ballPause--;
				
				scoreLabel.setLabel("Score: " + score);
				wall = NO_WALL;
				changeBallDirection();
			}
			
			// Did the ball hit the south wall ?
			if (wall == SOUTH) {
				credits--;
				creditsLabel.setLabel("Credits: " + credits);
				remove(ball);
				drawBall();
				pause(1000);
			}
			pause(ballPause);
		}
	}
	
	private void drawBoard() {
		int xPos = BRICK_SEP / 2;
		int yPos = BRICK_Y_OFFSET;
		int colorIndex = 0;
		Color brickColor = Color.RED;
		
		for (int j=0; j<NBRICK_ROWS; j++) {
			for (int i=0; i<NBRICKS_PER_ROW; i++) {
				 drawPlainBrick (xPos, yPos, brickColor);
				 xPos += BRICK_WIDTH + BRICK_SEP;
			}
			if (j % 2 == 1) {
				brickColor = getNextColor(colorIndex);
				colorIndex++;
				if (colorIndex > 4) colorIndex = 0;
			}
			xPos = BRICK_SEP / 2;
			yPos += BRICK_HEIGHT + BRICK_SEP;
		}
	}

	private void drawPlainBrick (int x, int y, Color color) {
		GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		brick.setColor(color);
		brick.setFillColor(color);
		brick.setFilled(true);
		
		add(brick);
	}
	
	private void drawPaddle() {
		PADDLE_Y_LOCATION = getHeight() - PADDLE_Y_OFFSET;
		paddle.setLocation((getWidth() - PADDLE_WIDTH) / 2, PADDLE_Y_LOCATION);
		paddle.setSize(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFillColor(Color.GRAY);
		paddle.setFilled(true);
		add(paddle);
	}
	
	private void drawBall() {
		ball.setLocation(WIDTH/2, HEIGHT/2);
		ball.setSize(BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFillColor(Color.DARK_GRAY);
		ball.setFilled(true);
		add(ball);
		
		angle = rgen.nextDouble(300, 330);
		vx = EAST;
		vy = SOUTH;
	}
	
	private void moveBall() {
		ball.movePolar(BALL_RADIUS/2, angle);
		wall = ballHitsWall();
		if (wall != NO_WALL) changeBallDirection();		
	}
	
	private GObject getCollidingObject() {
		GObject collider =  getElementAt(ball.getX(), ball.getY());
		if (collider != null) return(collider);
		
		collider = getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY());
		if (collider != null) return(collider);
		
		collider = getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2);
		if (collider != null) return(collider);
		
		collider = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
		if (collider != null) return(collider);
		
		return(null);
	}
	
	private int ballHitsWall() {
		if (vx == EAST && ball.getX() + BALL_RADIUS*2 > getWidth()) return (EAST);
		if (vx == WEST && ball.getX() < 0) return(WEST);
		if (vy == SOUTH && ball.getY() + BALL_RADIUS*2 > getHeight()) return(SOUTH);
		if (vy == NORTH && ball.getY() < 0) return (NORTH);
		return(NO_WALL);
	}
	
	private void changeBallDirection() {
		// Ball hits wall 
		if (wall == EAST) {
			vx = WEST;
			angle = vy == SOUTH ? angle -90 : angle + 90;
		}
		
		if (wall == SOUTH) {
			vy = NORTH;
			angle = vx == WEST ? angle -90 : angle + 90;
		}
		
		if (wall == WEST) {
			vx = EAST;
			angle = vy == NORTH ? angle -90 : angle + 90;
		}
		
		if (wall == NORTH) {
			vy = SOUTH;
			angle = vx == EAST ? angle -90 : angle + 90;
		}
		
		// Ball hits paddle
		if (wall == NO_WALL && vy == SOUTH) {
			vy = NORTH;
			angle = vx == WEST ? angle -90 : angle + 90;
		} else if (wall == NO_WALL && vy == NORTH) {
			vy = SOUTH;
			angle = vx == EAST ? angle -90 : angle + 90;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		double xPos = 0;
		// Check if mouse pointer is inside paddle boundaries
		if (e.getX() > paddle.getX() && e.getX() < paddle.getX() + PADDLE_WIDTH) {
			xPos = e.getX() - (PADDLE_WIDTH / 2);
			
			// Is paddle xPos out of Canvas ?
			xPos = xPos < 0 ? 0 : xPos;
			xPos = xPos + PADDLE_WIDTH > getWidth() ? (getWidth() - PADDLE_WIDTH) : xPos;
			
			paddle.setLocation(xPos, PADDLE_Y_LOCATION);	
		}
	}
	
	private Color getNextColor(int index) {
		switch (index) {
		case 0:	return(Color.ORANGE);
		case 1:	return(Color.YELLOW);
		case 2:	return(Color.GREEN);
		case 3:	return(Color.CYAN);
		default: return(Color.RED);
		}
	}
	
	private GRoundRect paddle = new GRoundRect(0, 0);
	private GOval ball = new GOval(0, 0);
	private int PADDLE_Y_LOCATION;
	private double angle = 0;
	private int vx, vy;
	private int wall;
	private int ballPause = 10; 
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
