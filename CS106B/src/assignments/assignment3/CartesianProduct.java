package assignments.assignment3;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of a Sorted Set on a custom class (Cartesian product). The sorting and set operations requires the custom class to
 * implement a comparator as well as the <b>equals</b> method.
 * @author Jonathan Puvilland
 *
 */
public class CartesianProduct {

	public static void main(String[] args) {
		Set<Character> set1 = new HashSet<Character>();
		Set<Character> set2 = new HashSet<Character>();
		SortedSet<Pair> cartesianProduct;

		set1.add('a');
		set1.add('b');
		set1.add('c');

		set2.add('x');
		set2.add('y');

		cartesianProduct = getCartesianProduct(set1, set2);

		System.out.println(cartesianProduct);

	}


	private static SortedSet<Pair> getCartesianProduct(Set<Character> set1, Set<Character> set2) {
		SortedSet<Pair> cartesianProduct = new TreeSet<Pair>();

		for(Character c1 : set1)
			for(Character c2 : set2)
				cartesianProduct.add(new Pair(c1, c2));

		return cartesianProduct;
	}
}

class Pair implements Comparable<Pair> {
	char firstElement;
	char secondElement;

	public Pair(char c1, char c2) {
		firstElement = c1;
		secondElement = c2;
	}

	public String toString() {
		return "(" + firstElement + ", " + secondElement + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstElement;
		result = prime * result + secondElement;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (firstElement != other.firstElement)
			return false;
		if (secondElement != other.secondElement)
			return false;
		return true;
	}

	@Override
	public int compareTo(Pair to) {
		if(this.equals(to)) return 0;

		if(this.firstElement < to.firstElement)
			return -1;

		if(this.firstElement > to.firstElement)
			return 1;

		if(this.secondElement < to.secondElement)
			return -1;

		return 1;
	}
}

