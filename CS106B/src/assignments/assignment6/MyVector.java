package assignments.assignment6;

import java.lang.reflect.Array;

/**
 * The MyVector class provides facilities to create and manage objects in a Vector.
 * Vectors are arrays of elements, accessible through an index. 
 * @author Jonathan Puvilland
 *
 * @param <ElemType> The type of elements to store in the Vector.
 */

public class MyVector<ElemType> {

	   private ElemType[] vector;
	   private int numElemnts;
	   private int maxElements;

	   public MyVector(Class<ElemType[]> clazz, int length)  {  
	      vector = clazz.cast(Array.newInstance(clazz.getComponentType(), length));
	      
	      numElemnts = 0;
	      maxElements = length;
	   }
	   
	   public int size() {
		   return numElemnts;
	   }
	   
	   public void add(ElemType elem) throws IndexOutOfBoundsException {
		   if(numElemnts < maxElements)
			   vector[numElemnts++] = elem;
		   else
			   throw(new IndexOutOfBoundsException("Vector is full, maximum " +  maxElements + " elements allowed."));
	   }
	   
	   public ElemType getAt(int index) throws IndexOutOfBoundsException {
		   if(index < this.size())
			   return vector[index];
		   else {
			   throw(new IndexOutOfBoundsException("Index " + index + " is out of bounds."));
		   }
	   }
	   
	   @SuppressWarnings("unchecked")
	public MyVector<ElemType> removeDuplicates() throws CloneNotSupportedException {
		   MyVector<ElemType> tempVector = new MyVector<ElemType>((Class<ElemType[]>) vector.getClass(), this.size());
		   Boolean[] keepElem = new Boolean[this.size()];
		   
		   for(int i = 0; i < keepElem.length; i++)
			   keepElem[i] = true;
		   
		   for(int i = 0; i < this.size(); i++)
			   for(int j = i + 1; j < this.size(); j++)
				   if(vector[i].equals(vector[j]))
					   keepElem[j] = false;
		   
		   for(int i = 0; i < this.size(); i++)
			   if(keepElem[i])
				   tempVector.add(this.getAt(i));
		   
		   return tempVector;
	   }
	   
	   public String toString() {
		   return null;
	   }
}
