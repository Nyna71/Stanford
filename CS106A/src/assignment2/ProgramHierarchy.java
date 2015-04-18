package assignment2;
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {
	
	private static final int BOX_WIDTH = 140;
	private static final int BOX_MID_WIDTH = BOX_WIDTH / 2;
	private static final int BOX_HEIGHT = 60;
	private static final int BOX_MID_HEIGHT = BOX_HEIGHT / 2;
	
	public void run() {
		int windowsWidth = getWidth();
		int windowsHeight = getHeight();
		
		// int diagramWidth = BOX_WIDTH + 40;
		int diagramHeight = BOX_HEIGHT * 3;
		
		GLabel label1 =  new GLabel("Program");
		GLabel label2 =  new GLabel("GraphicsProgram");
		GLabel label3 =  new GLabel("ConsoleProgram");
		GLabel label4 =  new GLabel("DialogProgram");
		
		int xPosBox1 = (windowsWidth/2) -  (BOX_WIDTH/2);
		int yPosBox1 = (windowsHeight/2) - (diagramHeight/2);
		
		int xPosBox2 = (windowsWidth/2) -  (BOX_WIDTH*3/2) - 20;
		int yPosBox2 = yPosBox1 + 2*BOX_HEIGHT;
		
		int xPosBox3 = xPosBox2 + 20 + BOX_WIDTH;
		int yPosBox3 = yPosBox2;
		
		int xPosBox4 = xPosBox3 + 20 + BOX_WIDTH;
		int yPosBox4 = yPosBox2;
		
		GRect box1 = new GRect(xPosBox1, yPosBox1, BOX_WIDTH, BOX_HEIGHT);
		GRect box2 = new GRect(xPosBox2, yPosBox2, BOX_WIDTH, BOX_HEIGHT);
		GRect box3 = new GRect(xPosBox3, yPosBox3, BOX_WIDTH, BOX_HEIGHT);
		GRect box4 = new GRect(xPosBox4, yPosBox4, BOX_WIDTH, BOX_HEIGHT);
		
		add(box1);
		add(box2);
		add(box3);
		add(box4);
		
		add(new GLine(xPosBox1+BOX_MID_WIDTH, yPosBox1+BOX_HEIGHT, xPosBox2+BOX_MID_WIDTH, yPosBox2));
		add(new GLine(xPosBox1+BOX_MID_WIDTH, yPosBox1+BOX_HEIGHT, xPosBox3+BOX_MID_WIDTH, yPosBox3));
		add(new GLine(xPosBox1+BOX_MID_WIDTH, yPosBox1+BOX_HEIGHT, xPosBox4+BOX_MID_WIDTH, yPosBox4));   
		
		label1.setLocation(xPosBox1+BOX_MID_WIDTH-label1.getWidth()/2, yPosBox1+BOX_MID_HEIGHT+label1.getAscent()/2);
		label2.setLocation(xPosBox2+BOX_MID_WIDTH-label2.getWidth()/2, yPosBox2+BOX_MID_HEIGHT+label2.getAscent()/2);
		label3.setLocation(xPosBox3+BOX_MID_WIDTH-label3.getWidth()/2, yPosBox3+BOX_MID_HEIGHT+label3.getAscent()/2);
		label4.setLocation(xPosBox4+BOX_MID_WIDTH-label4.getWidth()/2, yPosBox4+BOX_MID_HEIGHT+label4.getAscent()/2);
		
		add(label1);
		add(label2);
		add(label3);
		add(label4);
	}
}

