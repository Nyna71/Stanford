package assignments.assignment6;

import java.awt.Dimension;
import java.awt.Point;

class Rectangle {
	private Dimension size;
	private Point location;
	private int perimeter;
	private int area;
	
	Rectangle(int x, int y, int width, int height) {
		size = new Dimension(width, height);
		location = new Point(x, y);
		perimeter = 2 * width + 2 * height;
		area = width * height;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
}
