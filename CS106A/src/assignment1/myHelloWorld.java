package assignment1;
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class myHelloWorld extends GraphicsProgram {

	public void run() {
		GLabel myLabel = new GLabel("Hello, World");
		Font myFont = new Font ("Garamond", Font.CENTER_BASELINE , 36);
		
		myLabel.setColor(Color.RED);
		myLabel.setFont(myFont);
		
		double xPos = (this.getWidth() - myLabel.getWidth()) / 2;
		double yPos = (this.getHeight() + myLabel.getAscent()) / 2;
		
		add(myLabel, xPos, yPos);
	}
}
