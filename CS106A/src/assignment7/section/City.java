package assignment7.section;

import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;

public class City implements Comparable<City> {
	public City(String name) {
		this.name = name;
		destinations = new TreeSet<String>();
	}

	public String getName() {
		return name;
	}
	
	public void addDestination(String destination) {
		destinations.add(destination);
	}
	
	public boolean containsDestination(String destination) {
		return destinations.contains(destination);
	}
	
	public Iterator<String> getDestinations() {
		return destinations.iterator();
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}

	@Override
	public int compareTo(City toCity) {
		return this.getName().compareTo(toCity.getName());
	}
	
	private String name;
	private Set<String> destinations;
}
