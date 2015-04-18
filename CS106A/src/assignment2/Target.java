package assignment2;
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Target extends GraphicsProgram {
	
	private static final int  OUTER_CIRCLE_RADIUS = 72;
	
	public void run() {
		int windowsWidth = getWidth();
		int windowsHeight = getHeight();
		
		for (int i = 3; i > 0; i--) {
			int radius = OUTER_CIRCLE_RADIUS * i / 3;
			
			int xPos = (windowsWidth/2) - (radius/2);
			int yPos = (windowsHeight/2) - (radius/2);

			GOval circle = new GOval(xPos, yPos, radius, radius);			
			
			if (i == 2) {
				circle.setColor(Color.WHITE);
				circle.setFillColor(Color.WHITE);	
				} else {
					circle.setColor(Color.RED);
					circle.setFillColor(Color.RED);
			}		
			circle.setFilled(true);			
			add(circle);			
		}
	}
}
