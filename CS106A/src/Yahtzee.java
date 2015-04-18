/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		playerScores = new int[nPlayers+1][N_CATEGORIES+1];
		
		// Init playerScores table
		for(int i = 1; i < nPlayers+1; i++) {
			for(int j = 1; j < N_CATEGORIES+1; j++)
				playerScores[i][j] = -1;
			playerScores[i][LOWER_SCORE] = 0;
			playerScores[i][UPPER_SCORE] = 0;
			playerScores[i][TOTAL] = 0;
		}
		
		// Init Yahtzee dice values and statuses.
		for(int i = 0; i < 5; i++) diceArray[i] = new YahtzeeDice(0, true);	
				
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplayWrapper(getGCanvas(), playerNames);
		
		playGame();
	}

	private void playGame() {
		boolean gameOver = false;
		int nTurns = 0;
		int playerIndex = 0;
		
		while(!gameOver) {
			playerIndex = selectNextPlayer(playerIndex, nTurns);
			startPlayerTurn(playerIndex);
			endPlayerTurn(playerIndex);
			if(playerIndex == nPlayers) nTurns++;
			gameOver = isGameOver(nTurns, playerIndex);
		}
		display.printMessage("Game over. Here are the scores.");	
	}

	/**
	 * This method calculates either the current turn being played is the last turn of the game.
	 * @param nTurn is the turn number currently being played. A Yahtzee game has a maximum number of turns
	 * defined by the N_SCORING_CATEGORIES constant.  
	 * @param playerIndex is the index of the player currently playing. Index varies from [1..nPlayers].
	 * @return True if the last turn of the last player has been done
	 */
	private boolean isGameOver(int nTurns, int player) {
			// The game ends when the last player has played the last turn.
			return (player == nPlayers && nTurns == N_SCORING_CATEGORIES);
		}
	
	private void endPlayerTurn(int player) {
		YahtzeeMagicStub magic = new YahtzeeMagicStub();
		display.printMessage("Select a category.");
		int category = 0;
		boolean invalidCategory = true;

		while (invalidCategory) {
			category = display.waitForPlayerToSelectCategory();

			// Check if player has not filled a score yet in the chosen category
			if(playerScores[player][category] == -1) {
				invalidCategory = false;
			} else display.printMessage("Score already filled in that category. Choose another category.");
		}

		// Is the chosen category valid ?
		if(magic.checkCategory(getDiceValues(diceArray), category)) {
			playerScores[player][category] = getScore(category);
			if(category < UPPER_SCORE) {
				playerScores[player][UPPER_SCORE] += getScore(category);
			} else {
				playerScores[player][LOWER_SCORE] += getScore(category);
			}
			playerScores[player][TOTAL] += getScore(category);
			display.updateScorecard(category, player, getScore(category));			
		} else {
			IODialog dialog = getDialog();
			boolean confirm = dialog.readBoolean("This Category is invalid! Do you want to score 0 points ?", "Yes", "No");
			if(confirm) {	
			playerScores[player][category] = 0;
			display.updateScorecard(category, player, 0);
			} else endPlayerTurn(player);
		}
		display.updateScorecard(UPPER_SCORE, player, playerScores[player][UPPER_SCORE]);
		display.updateScorecard(LOWER_SCORE, player, playerScores[player][LOWER_SCORE]);
		display.updateScorecard(TOTAL, player, playerScores[player][TOTAL]);
	}

	/**
	 * This method calculates the score to be inputed in a category, depending on the dice roll.
	 * @param category is the category for which the score has to be computed.
	 * @return the score to be inputed in the category.
	 */
	private int getScore(int category) {
		int score = 0;
		
		switch(category) {
		case THREE_OF_A_KIND:
		case FOUR_OF_A_KIND:
		case CHANCE:
			for(int i = 0; i < diceArray.length; i++) {
				score += diceArray[i].diceValue;
			}
			return score;
		case FULL_HOUSE: return 25;
		case SMALL_STRAIGHT: return 30;
		case LARGE_STRAIGHT: return 40;
		case YAHTZEE: return 50;
		default:
			for(int i = 0; i < diceArray.length; i++) {
				score += diceArray[i].diceValue == category ? diceArray[i].diceValue : 0;
			}
			return score;
		}
	}

	/**
	 * This method starts a new turn for the player specified as parameter.
	 * It initializes the dices and ask the player to roll dices three times.
	 * @param player is the index of the player in a set of [1..nPlayers].
	 */
	private void startPlayerTurn(int player) {
		resetDices();
		display.waitForPlayerToClickRoll(player);
		rollDices();
		for(int i = 0; i < 2; i++) {
			display.displayDice(getDiceValues(diceArray));
			display.printMessage("Select the dices to re-roll and click Roll Again.");
			display.waitForPlayerToSelectDice();
			updateDicesStatus();
			rollDices();
		}
		display.displayDice(getDiceValues(diceArray));
	}

	/**
	 * This methods extract all diceValues from an array of YahtzeeDices, exporting all
	 * diceValues in an int array.
	 * @param diceArray is an array of YahtzeeDices, containing dice values and statuses.
	 * @return an int array of all dice values inside the YahtzeeDices.
	 */
	private int[] getDiceValues(YahtzeeDice[] diceArray) {
		int[] array = new int[diceArray.length];
		for(int i = 0; i < diceArray.length; i++) {
			array[i] = diceArray[i].diceValue;
		}
		return array;
	}

	/**
	 * This methods check on the canvas which dices are selected and set the roll status
	 * in the corresponding YahtzeeDice object.
	 */
	private void updateDicesStatus() {
		for (int i = 0; i < 5; i++) {
			diceArray[i].diceRollStatus = display.isDieSelected(i);
		}
	}

	/**
	 * This method resets the dice re-roll status to <i>true</i>, allowing to re-roll all dices
	 * when players start a new turn. 
	 */
	private void resetDices() {
		for (int i = 0; i < 5; i++) {
			diceArray[i].diceRollStatus = true;
		}
	}
	
	/**
	 * This method roll the dices from the dice array. Only dices with roll status to true,
	 * meaning dices selected by the player on the canvas, get rolled.
	 */

	private void rollDices() {
		for (int i = 0; i < 5; i++) {
			if(diceArray[i].diceRollStatus)
				diceArray[i].diceValue = rgen.nextInt(1, 6);
		}
	}
	
	/**
	 * This method calculate which's player turn must be started and invites him to play.
	 * @param player is the index of the player who played the last turn.
	 * @param nTurns is the current turn being played.
	 * @return the index of the player that must play his turn.
	 */

	private int selectNextPlayer(int player, int nTurns) {
		int i = (player % nPlayers) + 1;
		display.printMessage(playerNames[i-1] + "'s turn number: " + (nTurns+1));
		return(i);
	}
	

	/* Private instance variables */
	private int nPlayers;
	private YahtzeeDice[] diceArray = new YahtzeeDice[5];
	private int[][] playerScores;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
