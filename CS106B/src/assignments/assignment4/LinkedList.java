package assignments.assignment4;

class LinkedList { 
	
	public static void main(String[] args) {
		int[] cells1 = new int[] {4, 10, 2, 28, 14};
		int[] cells2 = new int[] {9, 27, 13};
		Cell cellList1;
		Cell cellList2;
		
		cellList1 = ConvertToList(cells1);
		cellList2 = ConvertToList(cells2);
		System.out.println(cellList1);
		System.out.println(cellList2);
		System.out.println(SumList(cellList1));
		System.out.println(SumList(cellList2));
		
		appendCellList(cellList1, cellList2);
		System.out.println(cellList1);
	}
	
	private static void appendCellList (Cell list1, Cell list2) {
		if(list1.next == null)
			list1.next = list2;
		else
			appendCellList(list1.next, list2);
	}

	private static int SumList(Cell cellList) {
		if(cellList == null)
			return 0;
		return cellList.value + SumList(cellList.next);
	}

	private static Cell ConvertToList(int[] cells) {
		Cell cellList = null;
		for(int i = cells.length - 1; i >= 0; i--)
			cellList = addCell(cellList, cells[i]);
		return cellList;
	}

	/**
	 * Adds the integer i as a new Cell at the head of the list
	 * @param cellList
	 * @param i
	 * @return
	 */
	private static Cell addCell(Cell cellList, int i) {
		Cell newCell = new Cell(i);
		
		newCell.next = cellList;
		return newCell;
	}
}
