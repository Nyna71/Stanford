package assignment2;
import java.awt.Color;

import	acm.program.*;
import	acm.graphics.*;
import	acm.util.*;

public class BookExamples extends GraphicsProgram {
	
	public void run() {
		GRect rect = new GRect(250,100,200,200);
		GOval oval = new GOval(250,100,200,200);
		oval.setFillColor(Color.CYAN);
		oval.setFilled(true);
		int nbrIn = 0;
		int nbrOut = 0;
		
		add(rect);
		add(oval);
		
		for (int i=0; i<20000; i++) {
			GLine dot = new GLine(0,0,0,0);
			dot.setColor(Color.RED);
			int x = rGen.nextInt(250, 450);
			int y = rGen.nextInt(100, 300);
			
			dot.setStartPoint(x, y);
			dot.setEndPoint(x, y);
			
			if (getElementAt(x, y) == oval)
				nbrIn++;
			else nbrOut++;
			
			add(dot);
		}
		add(new GLabel("NbrIn: " + nbrIn, 10, 10));
		add(new GLabel("NbrOut: " + nbrOut, 10, 20));
	}

	private RandomGenerator rGen = new RandomGenerator();
	
}
