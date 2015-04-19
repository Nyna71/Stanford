package assignments.assignment3;

public class ReverseString {

	public static void main(String[] args) {
		System.out.println(reverseString("Puvilland"));

	}

	private static String reverseString(String string) {
		if(string.length() == 0) return "";
		else return string.charAt(string.length()-1) + reverseString(string.substring(0, string.length() - 1));
	}

}
