package assignments.assignment6;

public class GenericsInAction {

	   public static void main(String[] args) throws CloneNotSupportedException  {  
		  MyVector<Integer> intVector = new MyVector<Integer>(Integer[].class, 10);   
	      intVector.add(1);
	      intVector.add(3);
	      intVector.add(1);
	      intVector.add(2);
	      intVector.add(2);
	      intVector.add(4);
	      intVector.add(1);
	      intVector.add(3);
	      intVector.add(3);
	      
	      for(int i = 0; i < intVector.size(); i++)
	    	  System.out.println(intVector.getAt(i));
	      
	      System.out.println("\nRemoving duplicates...");
	      
	      intVector = intVector.removeDuplicates();
	      
	      for(int i = 0; i < intVector.size(); i++)
	    	  System.out.println(intVector.getAt(i));
	   } 
}
