package assignment6;
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		names.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// Add the entry in the Hashmap, to be able to redraw the entry upon resizing.
		names.putIfAbsent(entry.getName(), entry);
		
		// Initiates a new color for the entry.
		if(nameColor.get(entry.getName()) == null) nameColor.put(entry.getName(), rgen.nextColor());
		
		double yScale = (getHeight() - GRAPH_MARGIN_SIZE * 2.0) / MAX_RANK;
		double oldX = 0;
		double oldY = 0;
		double newX = 0;
		double newY = 0;

		if(entry.getRank(START_DECADE) == 0)
			oldY =  getHeight() - GRAPH_MARGIN_SIZE*2;
		else
			oldY = entry.getRank(START_DECADE) * yScale;
		
		for(int i = 0; i < NDECADES; i++) {
			newX = (getWidth() / NDECADES) * i;
			newY = entry.getRank(START_DECADE + 10 * i) * yScale;
			
			// If rank = 0, there is no entry for that name on that decade. Set drawing at bottom of graph. 
			if(entry.getRank(START_DECADE + 10 * i) == 0) newY =  getHeight() - GRAPH_MARGIN_SIZE*2;
			
			GLabel label = new GLabel(entry.getName() + " " + entry.getRank(START_DECADE + 10 * i), newX, newY + GRAPH_MARGIN_SIZE);
			label.setColor(nameColor.get(entry.getName()));
			add(label);
			
			GLine line = new GLine(oldX, oldY + GRAPH_MARGIN_SIZE, newX, newY + GRAPH_MARGIN_SIZE);
			line.setColor(nameColor.get(entry.getName()));
			add(line);
			
			oldX = newX;
			oldY = newY;
		}
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawLines();
		printDecades();
		if(!names.isEmpty())
			for(String name : names.keySet())
				addEntry(names.get(name));
	}
	
	private void printDecades() {
		for(int i = 0; i < NDECADES; i++) {
			int x = (getWidth() / NDECADES) * i;  
			add(new GLabel(String.valueOf(START_DECADE + 10 * i), x, getHeight() - 8));;
		}	
	}

	private void drawLines() {
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
		for(int i = 1; i < NDECADES; i++) {
			int x = (getWidth() / NDECADES) * i;  
			add(new GLine(x, 0, x, getHeight()));
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private RandomGenerator rgen = new RandomGenerator();
	private HashMap<String, NameSurferEntry> names = new HashMap<String, NameSurferEntry>();
	private HashMap<String, Color> nameColor = new HashMap<String, Color>();
}
