package assignment3;

import java.awt.Color;
import acm.graphics.*;

/* Class: SignalTower */
/**
 * This class models a tower that can lights up upon receiving a signal, and forward the signal to the next
 * tower in the chain, propagating the signal from one tower to the next.
 */
public class SignalTower extends GCompound {

/* Constants variables */
	private static final int ON = 1;
	private static final int OFF = 0;
	
/* Constructor: SignalTower (name, next) */
/** Constructs a new Signal Tower with following parameters:
 * @name Name of the tower
 * @next Reference to to the next tower object. Set to <i>null</i> for the last tower of the chain. 	
 */
	public SignalTower(String name, SignalTower next) {
		towerName = name;
		nextTower = next;
		towerPic = createTower();
		towerLabel.setLabel(name);
		
		add(towerPic, 20, 20);
		add(towerLabel, 20 - towerLabel.getWidth() / 2 , 45 + towerLabel.getAscent());
	}

/* Method: lightOn() */
/**
 * This method lights the tower on and send a signal to the next tower in order to light on.	
 */
	public void lightOn() {
		towerLight = ON;
		towerPic.setFilled(true);
		if (nextTower != null) {
			nextTower.lightOn();
		}
	}

/* Method: lightOff() */
/**
 * This method lights the tower off and send a signal to the next tower in order to light off.	
 */
	public void lightOff() {
		towerLight = OFF;
		towerPic.setFilled(false);
		if (nextTower != null) {
			nextTower.lightOff();
		}
	}

/* Method: createTower() */
/**
 * This method creates the GPolygon that can be added to a GCanvas for drawing the tower.	
 */
	private GPolygon createTower() {
		GPolygon p = new GPolygon();
		
		p.addVertex(-20, 20);
		p.addVertex(-10, -10);
		p.addVertex(0, -5);
		p.addVertex(10, -20);
		p.addVertex(20, 20);
		p.setFillColor(Color.YELLOW);
		
		return(p);
	}
		
	private String towerName;
	private SignalTower nextTower;
	private GPolygon towerPic = new GPolygon();
	private GLabel towerLabel =  new GLabel("");
	
	private int towerLight = OFF;
}
