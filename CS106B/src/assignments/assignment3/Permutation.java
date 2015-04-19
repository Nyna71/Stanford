package assignments.assignment3;

class Permutation {

	public static void main(String[] args) {
		permute("", "ABCD");
	}

	private static void permute(String currentString, String remainingString) {
		// shows the currentString when remainingString is empty
		if(remainingString.length() == 0)
			System.out.println(currentString);
		else {
			// Iterate through each character in this string to generate permutations for each characters
			for(int i = 0; i < remainingString.length(); i++) {
				// Add current loop character to the current String
				String next = currentString + remainingString.charAt(i);

				// Remove current loop character from the remaining String
				String remaining = remainingString.substring(0, i) + remainingString.substring(i+1);

				// Recursively recall the permutations
				permute(next, remaining);
			}
		}
	}
}
