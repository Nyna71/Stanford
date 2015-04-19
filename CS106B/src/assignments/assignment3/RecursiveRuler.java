package assignments.assignment3;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RecursiveRuler {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Recursive Ruler Bands");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }

}

class MyPanel extends JPanel {
	private static final int PANEL_WIDTH = 1200;
	private static final int PANEL_HEIGHT = 250;

	private static final int RULER_OFFSET = 20;
	private static final int RULER_BASELINE = PANEL_HEIGHT - RULER_OFFSET;
	private static final int RULER_MIN_HEIGHT = 2;

    List<Ruler> rulers = new ArrayList<Ruler>();

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        // Draw first ruler
        int lefBound = RULER_OFFSET;
        int rightBound = PANEL_WIDTH - RULER_OFFSET;
        int height = RULER_BASELINE - RULER_OFFSET;
        addRuler(lefBound, rightBound, height);
    }

    private void addRuler(int lefBound, int rightBound, int height) {
		if(height > RULER_MIN_HEIGHT) {
			// Draw ruler at mid point
			int midPoint = (lefBound + rightBound) / 2;
			rulers.add(new Ruler(midPoint, RULER_BASELINE, height));

			// Add rulers left and right mid-point
			addRuler(lefBound, midPoint, height / 2);
			addRuler(midPoint, rightBound, height / 2);
		}
	}

	public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Baseline
        g.drawLine(RULER_OFFSET, RULER_BASELINE, PANEL_WIDTH - RULER_OFFSET, RULER_BASELINE);

        for(Ruler ruler : rulers)
        	ruler.paintRuler(g);
    }
}

class Ruler{
    int x, y, height;

    Ruler (int x, int y, int height) {
    	this.x = x;
    	this.y = y;
    	this.height = height;
    }

    public void paintRuler(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x, y - height);
    }
}
