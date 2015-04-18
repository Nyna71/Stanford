package assignment6;
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		int spaceIndex = line.indexOf(' ');
		name = line.substring(0, spaceIndex);
		for(int i = 0; i < NDECADES-1; i++) {
			int nextSpaceIndex = line.indexOf(' ', spaceIndex+1);
			String str = line.substring(spaceIndex+1, nextSpaceIndex);
			decadeList[i] = Integer.valueOf(str);
			spaceIndex = nextSpaceIndex;
		}
		decadeList[NDECADES-1] = Integer.valueOf(line.substring(spaceIndex+1)); 
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int index = (decade - START_DECADE) / 10;
		if(index >= 0 && index <= NDECADES) return decadeList[index];
		else return(0);
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String nameSurfer = "[" + name + "] [";
		for(int i : decadeList)
			nameSurfer += i + ",";
		nameSurfer = nameSurfer.substring(0, nameSurfer.length()-1) + "]";
		return nameSurfer;
	}
	
	private String name;
	private int[] decadeList = new int[NDECADES];
}

