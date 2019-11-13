import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import programmingproject.maze.Maze;

public class MazeModel {
	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

	private ArrayList<ChangeListener> changeListeners = new ArrayList<ChangeListener>(); 
	
	private Maze maze;
	
	public MazeModel(int x, int y) {
		
		maze = new Maze(x,y);
		
	}
	
	public MazeModel(int level) {
		maze = new Maze(level);	
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public boolean isSolved() {
		return this.maze.isSolved();
	}
	
	public boolean changePosition(String direction) {
		boolean moved = false;
		switch(direction) {
			case "right":
				moved = this.maze.moveToRight();
				break;
			case "left":
				moved = this.maze.moveToRight();
				break;
			case "straight":
				moved = this.maze.moveUp();
				break;
			case "down":
				moved = this.maze.moveDown();
				break;
		}
		if (moved) {
			fireChangeListener();
		}
		return moved;
		
	}
	
	private void fireChangeListener() {
		
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(new ChangeEvent(this));
		}
	}

	
	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}

}
