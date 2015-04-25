package assignments.assignment4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;

/**
 * Creates a Boggle Dice by extending a JButton. A Boggle Dice is as standard 6 faces dice, but
 * each faces holds a letter in place of a number. This Boggle game is composed of 16 predefined Dices
 * why are randomly selected upon Dice creation. 
 */
class BoggleDice extends JButton {
	private static final String STANDARD_DICES[]  = 
		{"AAEECR", "ABDFEO", "ACEOPS", "AFLKPS", "AISETP", "CIMOTU", "DEILRB", "DELRVY",
		 "DISTTN", "EERHNC", "EEINSU", "ENRTVR", "EIOSST", "ELRLRY", "RIMNQU", "HLNNRZ"};
	
	private String diceLetters;
	private	int upperFaceIndex;
	private static Random rnd = new Random();
	private Color bgColor = Color.WHITE;  
	
	/**
	 * Creates a Boggle Dice initialized with a 6-letters string. The upper-face of the Dice
	 * will be randomly selected from the parameter string upon object creation.
	 * @param letters A 6 letters string used to initialize the Dice.
	 */
	BoggleDice (String letters) {
		diceLetters = letters;
		upperFaceIndex = rnd.nextInt(6);
	}
	
	/**
	 * Creates a Boggle Dice initialized from a predefined list of Dices. The upper-face of the Dice
	 * will be randomly selected upon object creation.
	 * @param letters A 6 letters string used to initialize the Dice.
	 */
	BoggleDice () {
		this(STANDARD_DICES[rnd.nextInt(16)]);
		upperFaceIndex = rnd.nextInt(6);
	}
	
	/**
	 * Returns the letter on the face
	 * @return the letter on the face
	 */
	char getLetter() {
		return diceLetters.charAt(upperFaceIndex);
	}
	
	/**
	 * Sets the background color of the dice.
	 * @param color Green when part of a found word, white otherwise
	 */
	void setBgColor (Color color) {
		bgColor = color;
	}
	
	/**
	 * The Boggle Dice is graphically represented as a JButton, with the Dice letter being added as
	 * the JButton's text.
	 */
	public void paintComponent(Graphics g) {
		char faceLetter = diceLetters.charAt(upperFaceIndex);
		g.setFont(new Font("Arial", Font.BOLD, getWidth() / 2));
		setText(String.valueOf(faceLetter));
		setFocusable(false);
		setBackground(bgColor);
		super.paintComponent(g);
	}
	
	public String toString() {
		return diceLetters;
	}
}
