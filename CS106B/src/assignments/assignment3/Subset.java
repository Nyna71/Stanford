package assignments.assignment3;

class Subset {

	public static void main(String[] args) {
		subset("", "ABC");
	}

	private static void subset(String currentString, String remainingString) {
		// shows the currentString when remainingString is empty
		if(remainingString.length() == 0)
			System.out.println(currentString);
		else {
			// First choice: include first character in the subset
			subset(currentString + remainingString.charAt(0), remainingString.substring(1));

			// Second choice: do not include the first character in the subset
			subset(currentString, remainingString.substring(1));
		}
	}
}
