package assignments.assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MazePanel extends JPanel implements MouseListener, ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int mazeHeight;
	private int mazeWidth;
	private Maze maze;

	private JButton btSolve = new JButton("Solve Maze");
	private JButton btClear = new JButton("Clear Maze");

	public MazePanel(Maze maze) {
		this.setLayout(new BorderLayout());
		this.mazeHeight = maze.getMazeHeight();
		this.mazeWidth = maze.getMazeWidth();
		this.maze = maze;

		// Create a panel to host action buttons and place them on maze panel's south region
		JPanel plButton = new JPanel();
		plButton.setBorder(BorderFactory.createEtchedBorder()); //createLineBorder(Color.BLACK));
		this.add(plButton, BorderLayout.SOUTH);

		plButton.add(btSolve);
		plButton.add(btClear);
		btSolve.addActionListener(this);
		btClear.addActionListener(this);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		addMouseListener(this);
	}

	/**
	 * Goes through the maze solution and displays the cells in the solution path with green crosses.
	 * @param The initialised mazed to be solved.
	 * @param path
	 */
	private void showMazeSolutin(Stack<CellLocation> path) {
		while(!path.isEmpty()) {
			MazeCell cell = maze.getMazeCell(path.pop());
			cell.setCheckColor(Color.GREEN);
			cell.checkCell();
		}
		repaint();
	}

	/**
	 *	Clears all crosses from the maze panel.
	 */
	private void clearMaze() {
		for(int i = 0; i < maze.getMazeHeight(); i++)
			for(int j = 0; j < maze.getMazeWidth(); j++) {
				maze.getMazeCell(i, j).isChecked = false;
			}
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(mazeWidth * MazeCell.CELL_SIZE, mazeHeight * MazeCell.CELL_SIZE + 42);
	}

	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponents(g);
		g.setColor(Color.WHITE);

		g.clearRect(0, 0, mazeWidth * MazeCell.CELL_SIZE, mazeHeight * MazeCell.CELL_SIZE);

		for(int i = 0; i < mazeHeight; i++)
			for(int j = 0; j < mazeWidth; j++) {
				add(maze.getMazeCell(i, j));
				maze.getMazeCell(i, j).paintComponent(g);
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Did the user Clicked inside the maze's area ?
		if(e.getY() < mazeHeight * MazeCell.CELL_SIZE) {
			int rowIndex = e.getY() / MazeCell.CELL_SIZE;
			int colIndex = e.getX() / MazeCell.CELL_SIZE;
			maze.getMazeCell(rowIndex, colIndex).setCheckColor(Color.BLUE);
			maze.getMazeCell(rowIndex, colIndex).changeCheckStatus();
			maze.getMazeCell(rowIndex, colIndex).paintComponent(this.getGraphics());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Solve Maze"))
			showMazeSolutin(maze.solveMaze());

		if(ae.getActionCommand().equals("Clear Maze"))
			clearMaze();
	}
}