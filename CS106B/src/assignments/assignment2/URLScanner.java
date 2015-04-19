package assignments.assignment2;

import java.util.*;

public class URLScanner {

	private static final String URL = "<html><b><u><i>CS106 rules!</i></u></b></html>";

	public static void main(String[] args) {
		System.out.println(URL);
		System.out.println("URL is valid: " + isValidURL(URL));
	}

	private static boolean isValidURL(String url) {
		boolean isValid = true;
		Stack<String> urlStack = new Stack<String>();

		urlStack = extractTags(URL);
		while(!urlStack.isEmpty()) {
			if(urlStack.pop().replace("</", "<").equals(urlStack.firstElement())) {
				isValid = true;
				urlStack.remove(0);
			}
			else return false;
		}

		return isValid;
	}

	private static Stack<String> extractTags(String url) {
		Stack<String> tags = new Stack<String>();

		for(int i = 0; i < url.length(); i++) {
			if(url.charAt(i) == '<') {
				String tag = "";
				while(url.charAt(i) != '>')
					tag += url.charAt(i++);
				tag += url.charAt(i);
				tags.push(tag);
			}
		}

		return tags;
	}

}
