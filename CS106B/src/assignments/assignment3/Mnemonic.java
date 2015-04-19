package assignments.assignment3;

import java.util.HashMap;
import java.util.Map;


/**
 * Generates a list on mnemonics based on letters present on telephone dial numbers. Uses a smart recursion principle.
 * @author id922010
 *
 */
public class Mnemonic {

	static Map<Character, String> dials = new HashMap<Character, String>();

	public static void main(String[] args) {

		dials.put('0', "0");
		dials.put('1', "1");
		dials.put('2', "ABC");
		dials.put('3', "DEF");
		dials.put('4', "GHI");
		dials.put('5', "JKL");
		dials.put('6', "MNO");
		dials.put('7', "PRS");
		dials.put('8', "TUV");
		dials.put('9', "WXY");

		getMnemonics("", "0477613382");
	}

	private static void getMnemonics(String mnemonic, String rest) {
		if(rest.length() == 0)
			System.out.println(mnemonic);

		else {
			String options = dials.get(rest.charAt(0));
			for(int i = 0; i < options.length(); i++) {
				getMnemonics(mnemonic + String.valueOf(options.charAt(i)), rest.substring(1));
			}
		}
	}
}
