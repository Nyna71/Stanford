package assignment4;
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.*;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	}
	
	public void initLexicon() {
		BufferedReader lexBuf = null;
		String str = new String();
		
		try {
			lexBuf = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				str = lexBuf.readLine();
				if (str == null) break;
				lexicon.add(str.toUpperCase());
			}
			lexBuf.close();
		}
		catch(IOException ex) {
			System.out.println("Bad File");
		}
	}
	
	private ArrayList<String> lexicon = new ArrayList<String>();
	
}
