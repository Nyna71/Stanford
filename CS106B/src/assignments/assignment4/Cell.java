package assignments.assignment4;

class Cell {
	Cell next;
	int value;
	
	Cell (Cell next, int value) {
		this.next = next;
		this.value = value;
	}
	
	Cell (int value) {
		this(null, value);
	}
	
	public String toString() {
		String str = Integer.toString(value);
		if(next != null)
			str += ", " + next;
		return str;
	}
}
