package assignment3;
import acm.program.*;

import java.io.*;

public class FileExample extends ConsoleProgram {
	private static final String FILENAME = "KingLear.txt";

	public void run() {
		BufferedReader reader;
		int nbrLines = 0;
		int nbrWords = 0;
		int nbrChars = 0;
		
		reader = openFile(FILENAME);
		
		while(true) {
			String line = readLine(reader, FILENAME);
			if(line == null) break;
			println(line);
			nbrLines++;
			nbrWords += getNumberOfWords(line);
			nbrChars += line.length();
		}
		closeFile(reader, FILENAME);
		println("----------------------------------------");
		println("Nbr Lines: " + nbrLines);
		println("Nbr Words: " + nbrWords);
		println("Nbr Characters: " + nbrChars);
	}

	private int getNumberOfWords(String line) {
		int nbrWords = 0;
		boolean newWordStarted = false;
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(Character.isLetterOrDigit(c) && !newWordStarted) {
				nbrWords++;
				newWordStarted = true;
			}
			if(!Character.isLetterOrDigit(c) && newWordStarted) {
				newWordStarted = false;
			}
		}
		return nbrWords;
	}

	private String readLine(BufferedReader reader, String fileName) {
		try {
			return reader.readLine();
		}
		catch(IOException ex) {
			System.out.println("Cannot read file: "  + fileName);
			return null;
		}
	}

	private BufferedReader openFile(String fileName) {
		try {
			return new BufferedReader(new FileReader(fileName));
		}
		catch(IOException ex) {
			System.out.println("Cannot open file: "  + FILENAME);
			return null;
		}
	}

	private void closeFile(BufferedReader reader, String fileName) {
		try {
			reader.close();
		}
		catch(IOException ex) {
			System.out.println("Cannot close file: "  + FILENAME);
		}
	}

}