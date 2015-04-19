package assignments.assignment3;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a HashMap on a custom key (PointT class). Adding getting elements out of a custom map requires in the class
 * implementing the key the addition of the <b>hashCode</b> and <b>equals</b> methods.
 * @author Jonathan Puvilland
 *
 */

public class CityMap {
	private String name;
	private PointT location;

	public static void main(String[] args) {
		CityMap binche = new CityMap("Binche", new PointT(10, 10));
		CityMap mons = new CityMap("Mons", new PointT(20, 20));
		CityMap charleroi = new CityMap("Charleroi", new PointT(30, 30));
		CityMap liege = new CityMap("Liège", new PointT(30, 30));

		Map<PointT, CityMap> cities = new HashMap<PointT, CityMap>();

		cities.put(binche.location, binche);
		cities.put(mons.location, mons);
		cities.put(charleroi.location, charleroi);
		cities.put(liege.location, liege);

		System.out.println(cities);
		System.out.println(cities.get(new PointT(30, 30)));

	}

	public CityMap (String name, PointT location) {
		this.name = name;
		this.location = location;
	}

	public String toString() {
		return "[" + this.name + " " + this.location + "]";
	}
}


class PointT {
	int x;
	int y;

	public PointT(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		PointT other = (PointT) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
