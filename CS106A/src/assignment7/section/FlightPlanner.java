package assignment7.section;

import java.io.*;
import java.util.*;

import acm.program.*;
import acm.util.ErrorException;

public class FlightPlanner extends ConsoleProgram {

	public void init() {
		try {
			BufferedReader rd = new BufferedReader(
					new FileReader("flights.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				if (line.length() > 0) addCity(line);
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);}
		
		println("Welcome to Flight Planner!");
		println("Here is a list of cities in our database:");
		for(City city : cities)
			println(" [" + city.getName() + "] ");
		println("Let's plan a round-trip route!");
		
		while(starterCity == null) {
			String line = readLine("Enter the starting city: ");
			City enterCity = new City(line);
			if(cities.contains(enterCity))
				starterCity = cities.floor(enterCity);
			else
				println("Invalid city!");
		}
	}
	
	private void addCity(String line) {
		int separatorStartIndex = line.indexOf("->");
		int separatorEndIndex = separatorStartIndex + 2;
		
		String cityName = line.substring(0, separatorStartIndex - 1);
		String destinationName = line.substring(separatorEndIndex + 1);
		
		City city =  new City(cityName);
		
		// If the city does not exists in the Set, add it to set and add the destination
		if(cities.add(city))
			city.addDestination(destinationName);
		//If the city exists in the Set, get the city reference and add the destination
		else
		{
			city = cities.floor(city);
			city.addDestination(destinationName);
		}
			
	}

	public void run() {
		City nextCity = starterCity;
		route.add(nextCity);
		
		do {
			displayDestinations(nextCity);
			nextCity = getDestination(nextCity);
			route.add(nextCity);
		} while(nextCity.compareTo(starterCity) != 0);
		
		String routeDisplay = "";
		for(City city : route) {
			routeDisplay += city.getName() + " -> ";
		}
		println(routeDisplay.substring(0, routeDisplay.lastIndexOf(" -> ")));
			
	}
	
	private City getDestination(City currentCity) {
		City nextCity = null;
		while(nextCity == null) {
			String line = readLine("Where to you want to go from " + currentCity.getName() + "? ");
			City cityEntered = new City(line);
			if(cities.contains(cityEntered) && currentCity.containsDestination(line))
				nextCity = cities.floor(cityEntered);
			else
				println("Invalid destination!");
		}
		return nextCity;
	}

	private void displayDestinations(City currentCity) {
		println("From " + currentCity.getName() + " you can fly directly to:");
		Iterator<String> cityIterator = currentCity.getDestinations();
		while(cityIterator.hasNext())
			println(" " + cityIterator.next());
	}

	private TreeSet<City> cities = new TreeSet<City>();
	private ArrayList<City> route = new ArrayList<City>();
	private	City starterCity;
}
