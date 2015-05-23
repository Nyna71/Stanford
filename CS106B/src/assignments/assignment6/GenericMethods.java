package assignments.assignment6;

public class GenericMethods {

	static <ElemType extends Comparable<ElemType>> ElemType getMax(ElemType[] array) {
		ElemType maxElem = array[0];
		
		for(int i = 0; i < array.length; i++)
			if(array[i].compareTo(maxElem) > 0) maxElem = array[i];
		
		return maxElem;
	}
	
	public static void main(String[] args) {
		Integer[] intArray = {10, 5, 64, 9, 45, 5, 64};
		String[] stringArray = {"Jonathan", "Florence", "Hugo", "Pierre", "Alexis"};
		
		System.out.println("Max Interger: " + GenericMethods.getMax(intArray));
		System.out.println("Max String: " + GenericMethods.getMax(stringArray));

	}

}
