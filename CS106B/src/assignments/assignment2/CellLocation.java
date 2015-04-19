package assignments.assignment2;

class CellLocation {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colIndex;
		result = prime * result + rowIndex;
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
		CellLocation other = (CellLocation) obj;
		if (colIndex != other.colIndex)
			return false;
		if (rowIndex != other.rowIndex)
			return false;
		return true;
	}

	int rowIndex;
	int colIndex;

	public CellLocation (int row, int col) {
		rowIndex = row;
		colIndex = col;
	}

	@Override
	public String toString() {
		return "Cell(" + rowIndex + "," + colIndex + ")";
	}
}
