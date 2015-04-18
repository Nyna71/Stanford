package assignment1;
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class mySnowMan extends GraphicsProgram {

	public void run() {
		drawLabel();
		drawHead();
		drawTopBody();
		drawLowBody();
	}
	
	private void drawLabel() {
		GLabel myLabel = new GLabel("Here is my SnowMan", 120, 10);
		myLabel.setColor(Color.RED);
		add(myLabel);
	}
	
	private void drawHead() {
		GOval myHead = new GOval(150,30,50,50);
		add(myHead);
		add(new GOval(160,40,10,10));
		add(new GOval(180,40,10,10));
		add(new GLabel("v",173,60));
		add(new GOval(170,65,10,10));
	}

	private void drawTopBody() {
		GOval myTopBody = new GOval(125,80,100,100);
		myTopBody.setFillColor(Color.BLUE);
		myTopBody.setFilled(true);
		add(myTopBody);
		drawButtons();
	}
	
	private void drawLowBody() {
		GOval myLowBody = new GOval(100,180,150,150);
		myLowBody.setFillColor(Color.YELLOW);
		myLowBody.setFilled(true);
		add(myLowBody);
	}
	
	private void drawButtons() {
		add(new GRect(166,95,15,15));
		add(new GRect(166,120,15,15));
		add(new GRect(166,145,15,15));
	}
}
