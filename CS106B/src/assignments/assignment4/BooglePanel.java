package assignments.assignment4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Creates a graphical boggle board, consisting of a 4x4 letter dices  
 * @author Almera Nynaeve
 *
 */
class BooglePanel extends JPanel implements ActionListener {
	// Boggle board dimensions
	private static final int PANEL_WIDTH = 650;
	private static final int PANEL_HEIGHT = 420;
	private static final int TEXT_PANE_WIDTH = 150;
	
	// player vs computer scores
	private int myScore = 0;
	private int computerScore = 0;
	
	// List of words guessed by player and computer
	private List<String> myWords = new ArrayList<String>();
	private List<String> computerWords = new ArrayList<String>();
	
	// The French word lexicon
	BoggleLexicon lex = new BoggleLexicon();
	
	// Graphical components
	JTextPane playerPane = new JTextPane();
	JTextPane computerPane = new JTextPane();
	JTextField inputTF = new JTextField(10);
	JLabel myScoreLB = new JLabel(String.valueOf(myScore));
	JLabel computerScoreLB = new JLabel(String.valueOf(computerScore));
	JLabel statusLineLB = new JLabel();
	
	// The board of Boggle dices
	BoggleDice[][] board = new BoggleDice[4][4];

	/**
	 * The Boggle board pane consisting of
	 * - Top pane with scores
	 * - Left pane with player's list of guessed words
	 * - Right pane with computer's list of guessed words
	 * - Center pane with dices
	 * - Bottom pane with input entry and messages
	 */
	BooglePanel() {
		setLayout(new BorderLayout());
		this.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		addDicePane();
		addTitlePanel();
		addPlayerTextPane();
		addComputerTextPane();
		addEntryPane();
		
		inputTF.addActionListener(this);
	}

	/**
	 * Right pane with computer's list of guessed words
	 */
	private void addComputerTextPane() {
		computerPane.setBackground(Color.LIGHT_GRAY);
		computerPane.setBorder(BorderFactory.createEtchedBorder());
		computerPane.setEditable(false);
		computerPane.setPreferredSize(new Dimension(TEXT_PANE_WIDTH, this.getHeight()));
		add(computerPane, BorderLayout.EAST);
	}

	/**
	 * Left pane with player's list of guessed words
	 */
	private void addPlayerTextPane() {
		playerPane.setBackground(Color.LIGHT_GRAY);
		playerPane.setBorder(BorderFactory.createEtchedBorder());
		playerPane.setEditable(false);
		playerPane.setPreferredSize(new Dimension(TEXT_PANE_WIDTH, this.getHeight()));
		add(playerPane, BorderLayout.WEST);
	}
	
	/**
	 * Bottom pane with input entry and messages
	 */
	private void addEntryPane() {
		JPanel entryPanel = new JPanel();
		Font titleFont = new Font("Arial", Font.BOLD, 18);
		entryPanel.setLayout(new GridLayout(2, 2));
		entryPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JLabel lb1 = new JLabel("Enter a word (empty for exit): ");
		lb1.setFont(titleFont);
		inputTF.setFont(titleFont);
		
		entryPanel.add(lb1);
		entryPanel.add(inputTF);
		entryPanel.add(statusLineLB);
		this.add(entryPanel, BorderLayout.SOUTH);
	}

	/**
	 * Top pane with scores
	 */
	private void addTitlePanel() {
		JPanel titlePanel = new JPanel();
		Font titleFont = new Font("Arial", Font.BOLD, 18);
		titlePanel.setLayout(new GridLayout(1, 4));
		titlePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JLabel lb1 = new JLabel("My Score: ");
		lb1.setFont(titleFont);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lb2 = new JLabel("Computer Score: ");
		lb2.setFont(titleFont);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		myScoreLB.setFont(titleFont);
		computerScoreLB.setFont(titleFont);
		
		titlePanel.add(lb1);
		titlePanel.add(myScoreLB);
		titlePanel.add(lb2);
		titlePanel.add(computerScoreLB);
		this.add(titlePanel, BorderLayout.NORTH);
	}

	/**
	 * Center pane with dices
	 */
	private void addDicePane() {
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridLayout(4, 4, 5, 5));
		dicePanel.setBackground(Color.LIGHT_GRAY);
		dicePanel.setBorder(BorderFactory.createEtchedBorder());
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) {
				board[i][j] = new BoggleDice();
				dicePanel.add(board[i][j]);
		}
		add(dicePanel, BorderLayout.CENTER);
	}

	/**
	 * Triggers the action of either looking for words in the boggle or let computer plays 
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String word = ae.getActionCommand();
		cleanBoard(); // empty previously highlighted dices
		
		// Pre-Condition
		// Empty words means player gives-up to computer's turn 
		if(word.length() == 0)
			// findRemainingWords();
			generateWordsRecursive();
		
		// Pre-Condition
		// - word is at least 4 letters
		// - word is not yet on player's list
		// - word is part of lexicon
		if(word.length() >= 4)
			if(!myWords.contains(word) && lex.containsWord(word)) {

				// Check if word is part of board
				if(findWord(word.toUpperCase())) {
					myWords.add(word);
					myScore += word.length();
					playerPane.setText(myWords.toString());
					myScoreLB.setText(String.valueOf(myScore));
					repaint();
				}
				else {
					statusLineLB.setText("Word not present on board");
					cleanBoard();
				}
			}
			else statusLineLB.setText("Not a valid word");
		else statusLineLB.setText("Enter at least 4 characters");
		inputTF.setText("");
	}

	private void findRemainingWords() {
		// Go through the lexicon and try to find all remaining words
		for(String word : lex.getListOfWords()) {
			if(word.length() >= 4  && !myWords.contains(word) && 
					!computerWords.contains(word) && findWord(word.toUpperCase()))
				computerWords.add(word);
				computerScore += word.length();
				computerPane.setText(computerWords.toString());
				computerScoreLB.setText(String.valueOf(computerScore));
		}
		repaint();
	}
	
	private void generateWordsRecursive() {
		// Generate all possible words from a recursive approach
		String word = "";
		for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
            	generateWord(word, row, col);
	}

	private void generateWord(String word, int row, int col) {
		// Check if letter selection is out of bound or already used 
		if(row < 0 || row >= board.length || col < 0 || col >= board.length ||
				board[row][col].getBgColor() == Color.GREEN)
			return ;
		
		// Check if computer has generated a valid word
		if(word.length() >= 4  && !myWords.contains(word) && 
				!computerWords.contains(word) && lex.containsWord(word)) {
			System.out.println(word);
			computerWords.add(word);
			computerScore += word.length();
			computerPane.setText(computerWords.toString());
			computerScoreLB.setText(String.valueOf(computerScore));
		}
		
		// Generate all possible words starting from current board position, and adding
		// one of adjacent letters
		else {
			word += board[row][col].getLetter();
			board[row][col].setBgColor(Color.GREEN);
			generateWord(word, row-1, col-1);
            generateWord(word, row-1,   col);
            generateWord(word, row-1, col+1);
            generateWord(word,   row, col-1);
            generateWord(word,   row, col+1);
            generateWord(word, row+1, col-1);
            generateWord(word, row+1,   col);
            generateWord(word, row+1, col+1);
		}
		board[row][col].setBgColor(Color.WHITE);
	}

	/**
	 * Empty previously highlighted dices
	 */
	private void cleanBoard() {
		for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
            	board[row][col].setBgColor(Color.WHITE);
		repaint();
	}

	/**
	 * Returns true if the word can be found in the boggle board
	 * @param word
	 * @return
	 */
    private boolean findWord(String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (findWord(word, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returns true if the (sub)word can be found in the boggle board starting from the
     * specified coordinates
     * @param word
     * @param row
     * @param col
     * @return
     */
    private boolean findWord(String word, int row, int col) {
        if (word.length() == 0) {
            return true;
        }
        else if (row < 0 || row >= board.length || 
                 col < 0 || col >= board.length || 
                 board[row][col].getLetter() != word.charAt(0)) {
            return false;
        }
        else {
            BoggleDice safe = board[row][col];
            board[row][col].setBgColor(Color.GREEN);
            String rest = word.substring(1, word.length());
            boolean result = findWord(rest, row-1, col-1) ||
                             findWord(rest, row-1,   col) ||
                             findWord(rest, row-1, col+1) ||
                             findWord(rest,   row, col-1) ||
                             findWord(rest,   row, col+1) ||
                             findWord(rest, row+1, col-1) ||
                             findWord(rest, row+1,   col) ||
                             findWord(rest, row+1, col+1);
            board[row][col] = safe;
            return result;
        }
    }
}
