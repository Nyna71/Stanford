package assignments.assignment3;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of a Sorted Set on a custom class (EntryT). The sorting and set operations requires the custom class to implement
 * a comparator.
 * @author Jonathan Puvilland
 *
 */
public class EntryT implements Comparable<EntryT> {
	private String firstName;
	private String lastName;
	private String phoneNumber;

	public static void main(String[] args) {
		EntryT jonathan = new EntryT("Jonathan", "Puvilland", "067/55.50.17");
		EntryT florence = new EntryT("Florence", "Corbisier", "067/55.50.17");
		EntryT david = new EntryT("David", "Puvilland", "067/55.50.17");
		EntryT duplicate = new EntryT("Jonathan", "Puvilland", "067/55.50.17");

		SortedSet<EntryT> names = new TreeSet<EntryT>();

		names.add(jonathan);
		names.add(david);
		names.add(florence);
		names.add(duplicate);

		System.out.println(names);
	}

	public EntryT(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int compareTo(EntryT to) {
		String thisFullName = this.lastName + this.firstName;
		String toFullName = to.lastName + to.firstName;
		return thisFullName.compareTo(toFullName);
	}

	@Override
	public String toString() {
		return "[" + lastName + ", " + firstName + " ("+ phoneNumber + ")]";
	}

}
