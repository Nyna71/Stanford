package assignments.assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Markov {

	private static final String  MODEL_TEXT = "Informatica Metadata Manager is a web-based metadata management tool that you can use to browse and "
			+ "analyze metadata from disparate metadata repositories. Metadata Manager helps you understand and manage how information and "
			+ "processes are derived. It also helps you understand the fundamental relationships between information and processes, "
			+ "and how they are used.";

	private static final int MODEL_LEVEL = 3;

	public static void main(String[] args) {
		Map<String, ArrayList<Character>> markov = new HashMap<String, ArrayList<Character>>();
		String seed = "";
		int maxSeedOccurence = 0;

		readModelText(markov);

		for(String key : markov.keySet()) {
			if(markov.get(key).size() > maxSeedOccurence) {
				maxSeedOccurence = markov.get(key).size();
				seed = key;
			}
		}

		System.out.println(writeModel(markov, seed));
	}

	private static String writeModel(Map<String, ArrayList<Character>> markov, String seed) {
		String newText = "";

		for(int i = 0; i < MODEL_TEXT.length(); i++) {
			if(markov.containsKey(seed)) {
				Random r = new Random();
				int j = r.nextInt(markov.get(seed).size());
				newText += markov.get(seed).get(j);
				seed = "" + seed.substring(1) + markov.get(seed).get(j);
			} else break;
		}
		return newText;
	}

	private static void readModelText(Map<String, ArrayList<Character>> markov) {
		for(int i = 0; i < MODEL_TEXT.length() - MODEL_LEVEL ; i++) {
			String key = MODEL_TEXT.substring(i, i + MODEL_LEVEL);
			Character nextCharacter = MODEL_TEXT.charAt(i + MODEL_LEVEL);
			addKey(markov, key, nextCharacter);
		}
	}

	private static void addKey(Map<String, ArrayList<Character>> markov, String key, Character nextCharacater) {
		ArrayList<Character> characters;

		if(markov.containsKey(key)) {
			characters = markov.get(key);
			characters.add(nextCharacater);
		}
		else {
			characters = new ArrayList<Character>();
			characters.add(nextCharacater);
			markov.put(key, characters);
		}
	}

}
