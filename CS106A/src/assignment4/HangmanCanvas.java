package assignment4;
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		this.removeAll();
		incorrectChar = "";
		incorrectLabel.setLabel(incorrectChar);
		nbrIncorrectGuess = 0;
		xPos = getWidth() / 2;
		yPos = getHeight() * 0.4;
		
		wordLabel.setLocation(xPos / 4, yPos + BODY_LENGTH + LEG_LENGTH);
		wordLabel.setFont("MonoSpaced-20");
		incorrectLabel.setLocation(xPos / 4, yPos + BODY_LENGTH + LEG_LENGTH + wordLabel.getAscent());
		incorrectLabel.setFont("MonoSpaced-15");
		
		drawScaffold();
		add(wordLabel);
		add(incorrectLabel);
	}
	
	private void drawScaffold() {
		double x = xPos - BEAM_LENGTH;
		double y = yPos - (BODY_LENGTH / 2) - HEAD_RADIUS - ROPE_LENGTH;
		add(new GLine(x, y, x, SCAFFOLD_HEIGHT));
		add(new GLine(x, y, x + BEAM_LENGTH, y));
		add(new GLine(x + BEAM_LENGTH, y, x + BEAM_LENGTH, y + ROPE_LENGTH));
	}
	
	private void drawHead() {
		double x = xPos - (HEAD_RADIUS / 2);
		double y = yPos - (BODY_LENGTH / 2) - HEAD_RADIUS;
		add(new GOval(x, y, HEAD_RADIUS, HEAD_RADIUS));
	}
	
	private void drawBody() {
		double x = xPos;
		double y = yPos - BODY_LENGTH / 2;
		add(new GLine(x, y, x, y + BODY_LENGTH));
	}
	
	private void drawLefArm() {
		double x = xPos;
		double y = yPos - BODY_LENGTH / 2 + ARM_OFFSET_FROM_HEAD;
		add(new GLine(x, y, x - UPPER_ARM_LENGTH, y));
		add(new GLine(x - UPPER_ARM_LENGTH, y, x - UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH));
	}
	
	private void drawRightArm() {
		double x = xPos;
		double y = yPos - BODY_LENGTH / 2 + ARM_OFFSET_FROM_HEAD;
		add(new GLine(x, y, x + UPPER_ARM_LENGTH, y));
		add(new GLine(x + UPPER_ARM_LENGTH, y, x + UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH));
	}
	
	private void drawLefLeg() {
		double x = xPos;
		double y = yPos + BODY_LENGTH / 2;
		add(new GLine(x, y, x - HIP_WIDTH, y));
		add(new GLine(x - HIP_WIDTH, y, x - HIP_WIDTH, y + LEG_LENGTH));
	}
	
	private void drawRightLeg() {
		double x = xPos;
		double y = yPos + BODY_LENGTH / 2;
		add(new GLine(x, y, x + HIP_WIDTH, y));
		add(new GLine(x + HIP_WIDTH, y, x + HIP_WIDTH, y + LEG_LENGTH));
	}
	
	private void drawLefFoot() {
		double x = xPos - HIP_WIDTH;
		double y = yPos + BODY_LENGTH / 2 + LEG_LENGTH;
		add(new GLine(x, y, x - FOOT_LENGTH, y));
	}
	
	private void drawRightFoot() {
		double x = xPos + HIP_WIDTH;
		double y = yPos + BODY_LENGTH / 2 + LEG_LENGTH;
		add(new GLine(x, y, x + FOOT_LENGTH, y));
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		wordLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectChar += Character.toUpperCase(letter);
		incorrectLabel.setLabel(incorrectChar);
		
		switch(++nbrIncorrectGuess) {
		case 1: drawHead(); break;
		case 2: drawBody(); break;
		case 3: drawLefArm(); break;
		case 4: drawRightArm(); break;
		case 5: drawLefLeg(); break;
		case 6: drawRightLeg(); break;
		case 7: drawLefFoot(); break;
		case 8: drawRightFoot(); break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 344;
	private static final int BEAM_LENGTH = 100;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 46;
	private static final int BODY_LENGTH = 120;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 52;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 68;
	private static final int FOOT_LENGTH = 28;
	
	private GLabel wordLabel = new GLabel("");
	private GLabel incorrectLabel = new GLabel("");
	private String incorrectChar = "";
	private int nbrIncorrectGuess = 0;
	
	private double xPos = 0;
	private double yPos = 0;
}
