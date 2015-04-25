package assignments.assignment4;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BoggleProgram {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        runBoggle();
		    }
		});
	}
	
	private static void runBoggle() {
		initGUI();
		playerTurn();
	}

	private static void playerTurn() {
		
	}

	private static void initGUI() {
		JFrame fr = new JFrame ("Boogle");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BooglePanel booglePnl = new BooglePanel();
		fr.setContentPane(booglePnl);
		fr.setBounds(50, 50, booglePnl.getWidth(), booglePnl.getHeight());
		
		fr.setVisible(true);
	}
}