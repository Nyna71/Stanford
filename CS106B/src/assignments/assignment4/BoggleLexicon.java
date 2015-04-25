package assignments.assignment4;

import java.io.*;
import java.util.*;

/**
 * A small lexicon of French words read from file, used to validate user's input and generate
 * all possible Boggle word solutions.
 * @author Nynaeve Almera
 *
 */
public class BoggleLexicon {
	private ArrayList<String> lexicon = new ArrayList<String>();
	
	/** Returns the word at the specified index. */
	public boolean containsWord(String word) {
		return lexicon.contains(word.toUpperCase());
	}
	
	BoggleLexicon() {
		BufferedReader lexBuf = null;
		String str;
		
		try {
			lexBuf = new BufferedReader(new FileReader("src/assignments/assignment4/liste_francais.txt"));
			while (true) {
				str = lexBuf.readLine();
				if (str == null) break;
				lexicon.add(str.toUpperCase());
			}
			lexBuf.close();
		}
		catch(IOException ex) {
			System.out.println("Bad File: " + ex);
		}
	}
	
	/**
	 * Returns the complete lexicon as an array of Strings
	 * @return
	 */
	public ArrayList<String> getListOfWords() {
		return lexicon;
	}
}
