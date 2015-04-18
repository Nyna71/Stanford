package assignment4;
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

	private static final int NBR_GUESS = 8;
	
    public void run() {

    	lex.initLexicon();
    	
    	for (int i =0 ; i < 5; i++) {

        	int nbrGuessLeft = NBR_GUESS;
        	boolean	wordGuessed = false;
        	boolean gameOver = false;	
        	
    		wordToGuess = chooseRandomWord();
    		wordPattern = getPattern(wordToGuess);

    		initBoard();		

    		while (!gameOver) {
    			println("The word to guess now looks like this: " + wordPattern);
    			canvas.displayWord(wordPattern);

    			if (nbrGuessLeft > 1) println("You have " + nbrGuessLeft + " guesses left.");
    			else println("You only have 1 guess left!");

    			char c = readChar();
    			wordPattern = updatePattern(c);

    			if (patternUpdated) println("That guess is correct!");
    			else {
    				println("There are no " + c + "'s in the word.");
    				canvas.noteIncorrectGuess(c);
    			}

    			// Update end of game conditions
    			wordGuessed = wordPattern.equals(wordToGuess);
    			if (!patternUpdated) nbrGuessLeft--;

    			gameOver = (nbrGuessLeft == 0) || wordGuessed;
    		}
    		if (wordGuessed) {
    			println("You guessed the word: " + wordToGuess);
    			println("You win!");
    			canvas.displayWord(wordPattern);
    		}
    		else {
    			println("You are completly hung!");
    			println("The word was: " + wordToGuess);
    		}
    	}
	}
		
	private String chooseRandomWord() {
		return lex.getWord(rgen.nextInt(0, lex.getWordCount()));
	}
	
	private String getPattern(String wordToGuess) {
		String wordPattern = "";
		for (int i = 0; i < wordToGuess.length(); i++) wordPattern += "-";
		return wordPattern;
	}

	private char readChar() {
		while (true) {
			String str = readLine("Your guess: ");
			if (str.length() != 1 || str.toLowerCase().charAt(0) < 'a' || str.toLowerCase().charAt(0) > 'z')
				println ("Wrong character! Please enter a single character.");
			else return str.toUpperCase().charAt(0);
		}
	}
	
	private String updatePattern(char c) {
		String str = "";
		patternUpdated = false;
		
		for (int i = 0; i < wordToGuess.length(); i++) {
			if (wordToGuess.charAt(i) == c) {
				str += c;
				patternUpdated = true;
			}
			else str += wordPattern.charAt(i);
		}
		return str;
	}

	private void initBoard() {
		println("Welcome to Hangman!");
		canvas.reset();
	}
	
	public void init() {
		 canvas = new HangmanCanvas();
		 add(canvas);
		}
	
    private RandomGenerator rgen = new RandomGenerator();
    private HangmanLexicon lex = new HangmanLexicon();
    private String wordToGuess;
    private String wordPattern;
    private boolean patternUpdated;
    private HangmanCanvas canvas;
}
