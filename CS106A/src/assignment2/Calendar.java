package assignment2;
import acm.program.*;
import acm.graphics.*;

public class Calendar extends GraphicsProgram {
	
	/* The number of days in the month */
	private static final int DAYS_IN_MONTH = 31;
	
	/* The day of the week on which the month starts */
	/* (Sunday = 0, Monday = 1, Tuesday = 2, and so on) */
	private static final int DAY_MONTH_STARTS = 6;
	
	/* The width in pixels of a day on the calendar */
	private static final int DAY_WIDTH = 40;
	
	/* The height in pixels of a day on the calendar */
	private static final int DAY_HEIGHT = 30;
	
	public void run() {
		int nbrWeeks = DAYS_IN_MONTH / 7;
		int remainingDays = DAYS_IN_MONTH - (7*nbrWeeks);
		
		if (remainingDays > 0) {
			if (DAY_MONTH_STARTS + remainingDays > 7) nbrWeeks += 2;
				else  nbrWeeks += 1;
		}
		
		drawWeeks(nbrWeeks);
		addDays();		
	}
		
	private void drawWeeks(int nbrWeeks) {
		for (int i=0; i<nbrWeeks; i++) {
			int yPos = i * DAY_HEIGHT;
			for (int j=0; j<7; j++) {
				int xPos = j *  DAY_WIDTH;
				add(new GRect(xPos, yPos, DAY_WIDTH, DAY_HEIGHT));
			}
		}
	}
	
	private void addDays() {
		for (int i=0; i<DAYS_IN_MONTH; i++) {
			int xPos = ((i+DAY_MONTH_STARTS)) % 7 * DAY_WIDTH;
			int yPos = (i+DAY_MONTH_STARTS) / 7 * DAY_HEIGHT;
			
			add(new GLabel("" + (i+1), xPos+2, yPos+12));
		}
	}	
}
