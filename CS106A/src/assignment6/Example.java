package assignment6;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;



public class Example extends GraphicsProgram {
	
	public void run() {
		
		System.out.println("Enter 3 points");
		addMouseListeners();
		
	}
	
	private void drawTriangle() {
		add(new GLine(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY()));
		add(new GLine(points[1].getX(), points[1].getY(), points[2].getX(), points[2].getY()));
		add(new GLine(points[2].getX(), points[2].getY(), points[0].getX(), points[0].getY()));
	}
	
	private void drawCircle(GPoint point)  {
		GOval circle = new GOval(point.getX(), point.getY(), 1, 1);
		circle.setFillColor(Color.GRAY);
		circle.setColor(Color.GRAY);
		circle.setFilled(true);
		add(circle);
	}
	
	private GPoint getHalDistance(GPoint p1, GPoint p2) {
		double x = 0;
		double y = 0;

		x = p1.getX() + (p2.getX() - p1.getX()) / 2;
		y = p1.getY() + (p2.getY() - p1.getY()) / 2;
		
		return new GPoint(x, y);
	}

	public void mouseClicked(MouseEvent e) {
		if(nbrClick < 3) {
			points[nbrClick] = new GPoint(e.getX(), e.getY());
			nbrClick++;
		}
		
		if (nbrClick >= 3 && !isTriangleVisible) {
			System.out.println("Draw triangle");
			drawTriangle();
			isTriangleVisible = true;
			currentPoint = points[rgen.nextInt(0, 2)];
			drawCircle(currentPoint);
		}
		
		if (nbrClick >= 3 && isTriangleVisible) {
			for(int i = 0; i < 5000; i++) {
				// Get new Point
				GPoint newPoint = points[rgen.nextInt(0, 2)];
				currentPoint = getHalDistance(currentPoint, newPoint);
				drawCircle(currentPoint);				
			}
		}
	}

	private int nbrClick = 0;
	private GPoint[] points = new GPoint[3];
	private GPoint currentPoint;
	private boolean isTriangleVisible = false;
	RandomGenerator rgen = new RandomGenerator();
	
}