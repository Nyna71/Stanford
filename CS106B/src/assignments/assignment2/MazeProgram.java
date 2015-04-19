package assignments.assignment2;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MazeProgram {
	static JFrame frame = new JFrame("Maze");
	static Maze maze = new Maze(20, 12);
	static JButton btSolve = new JButton("Solve Maze");
	static JButton btClear = new JButton("Clear Maze");

	public static void main(String[] args) {
		maze.initiliaseMaze();
		initialiseframe();
	}


	private static void initialiseframe() {
		// Create the frame window and add the Maze Panel to the center.
		frame.setBounds(100, 100, 200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(new MazePanel(maze));

		frame.pack();
		frame.setVisible(true);
	}
}
