package assignment6;
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import acm.util.ErrorException;

import java.io.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		
		nameTF = new JTextField(20);
		graphBT = new JButton("Graph");
		clearBT = new JButton("Clear");
		
		add(new JLabel("Name"), SOUTH);
		add(nameTF, SOUTH);
		add(graphBT, SOUTH);
		add(clearBT, SOUTH);
		add(graph);
		
		graph.update();
		
		addActionListeners();
		nameTF.addActionListener(this);
	}
	
	public void run() {
		try {
			BufferedReader rd = new BufferedReader(
					new FileReader("names-data.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				NameSurferEntry name = new NameSurferEntry(line);
				names.put(name.getName(), name);
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);}
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			NameSurferEntry entry = names.get(nameTF.getText());
			if(entry != null) graph.addEntry(entry);
		}
		
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
		
		if (e.getSource() == nameTF) {
			NameSurferEntry entry = names.get(nameTF.getText());
			if(entry != null) graph.addEntry(entry);
		}
	}
	
	private NameSurferGraph graph;
	private JTextField nameTF;
	private JButton graphBT;
	private JButton clearBT;
	private HashMap<String, NameSurferEntry> names = new HashMap<String, NameSurferEntry>();
}
