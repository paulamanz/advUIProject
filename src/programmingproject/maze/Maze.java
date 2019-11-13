package programmingproject.maze;

import java.awt.Point;

import javafx.util.Pair;

public class Maze {
	private Cell[][] maze;
	private Point whereIsRegi;
	private Point whereIsTreasure;
	private boolean solved;
	private int cols;
	private int rows;
	
	
	public Maze(int row, int col) {
		this.maze = new Cell[row][col];
		this.cols = col;
		this.rows = row;
		this.whereIsRegi = new Point(0,0);
		this.solved = false;
		System.out.println("Maze created");
		initMaze();
	}
	
	public Maze(int level) {
		this.solved=false;
		switch (level) {
		case 1:
			initMazeLevelOne();
			break;
		case 2: 
			initMazeLevelTwo();
			break;
		}
		System.out.println("Maze created");
		
	}
	
	public void initMazeLevelOne() {
		this.maze = new Cell[6][6];
		this.cols = 6;
		this.rows = 6;
		this.whereIsRegi = new Point(3,2);
		
		for(int x = 0; x < maze.length; x++) { 
			for (int y = 0; y < maze[x].length; y++) { 
				maze[x][y] = new Cell(CellObject.BLANK); 
				System.out.println("Created cell ("+ x + ","+ y + ")");
			} 
		}
		
		maze[(int) this.whereIsRegi.getX()][(int) this.whereIsRegi.getY()].AddRegi();
		
		
		//ADD SPECIFIC CELLS, THIS WILL BE DONE TAKING INTO ACCOUNT THE LEVELS
		maze[0][5].setState(CellObject.OBSTACLE);
		maze[4][0].setState(CellObject.OBSTACLE);
		maze[3][5].setState(CellObject.OBSTACLE);
		maze[5][3].setState(CellObject.OBSTACLE);
		maze[5][5].setState(CellObject.TREASURE);
		this.whereIsTreasure = new Point (5,5);
		
	}
	
	public void initMazeLevelTwo() {
		this.maze = new Cell[6][6];
		this.cols = 6;
		this.rows = 6;
		this.whereIsRegi = new Point(0,1);
		
		for(int x = 0; x < maze.length; x++) { 
			for (int y = 0; y < maze[x].length; y++) { 
				maze[x][y] = new Cell(CellObject.BLANK); 
				System.out.println("Created cell ("+ x + ","+ y + ")");
			} 
		}
		
		maze[(int) this.whereIsRegi.getX()][(int) this.whereIsRegi.getY()].AddRegi();
		
		//ADD SPECIFIC CELLS, THIS WILL BE DONE TAKING INTO ACCOUNT THE LEVELS
		maze[0][0].setState(CellObject.OBSTACLE);
		maze[4][3].setState(CellObject.OBSTACLE);
		maze[3][0].setState(CellObject.OBSTACLE);
		maze[1][1].setState(CellObject.OBSTACLE);
		maze[5][5].setState(CellObject.TREASURE);
		this.whereIsTreasure = new Point (5,5);
		
	}
	public Cell[][] getMaze() {
		return maze;
	}

	public void setMaze(Cell[][] maze) {
		this.maze = maze;
	}

	public Point getWhereIsRegi() {
		return whereIsRegi;
	}

	public void setWhereIsRegi(Point whereIsRegi) {
		this.whereIsRegi = whereIsRegi;
	}

	public boolean isSolved() {
		//this should go in the movements, not here. Will change it later.
		if (this.whereIsRegi.getX() == this.whereIsTreasure.getX() && this.whereIsRegi.getY() == this.whereIsTreasure.getY()) {
			solved = true;
		}
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void initMaze() {
		
		for(int x = 0; x < maze.length; x++) { 
			for (int y = 0; y < maze[x].length; y++) { 
				maze[x][y] = new Cell(CellObject.BLANK); 
				System.out.println("Created cell ("+ x + ","+ y + ")");
			} 
		}
		
		
		maze[(int) this.whereIsRegi.getX()][(int) this.whereIsRegi.getY()].AddRegi();
		
	}
	
	public boolean moveToRight() {
		boolean moved = false;
		int row=(int) whereIsRegi.getX();
		int col = (int) whereIsRegi.getY();
		
		
		if((col+1) < this.cols) {
			if (maze[row][col+1].AddRegi()) {
				moved = true;
				this.maze[row][col].setToBlank();
				whereIsRegi.setLocation(row, col+1); 
				System.out.println("Now Regi is in: ("+ row+ ","+ (col +1)+")");
			}
		}else {
			System.out.println("Not possible to move!");
		}
		
		return moved;
	}
	
	public boolean moveToLeft() {
		
		boolean moved = false;
		int row=(int) whereIsRegi.getX();
		int col = (int) whereIsRegi.getY();
		
		
		
		if((col-1) >= 0) {
			if (maze[row][col-1].AddRegi()) {
				moved = true;
				this.maze[row][col].setToBlank();
				whereIsRegi.setLocation(row, col-1);
				System.out.println("Now Regi is in: ("+row+ ","+ (col -1) +")");
			}
		}
		
		return moved;
	}
	
	public boolean moveUp() {
		boolean moved = false;
		
		int row=(int) whereIsRegi.getX();
		int col = (int) whereIsRegi.getY();
		
		
		if((row-1) >= 0) {
			if (maze[row-1][col].AddRegi()) {
				moved = true;
				this.maze[row][col].setToBlank();
				whereIsRegi.setLocation(row-1, col);
				System.out.println("Now Regi is in: ("+ (row-1)+ ","+ (col)+")");
			}
		}
		
		return moved;
	}
	
	public boolean moveDown() {
		
		boolean moved = false;
		int row=(int) whereIsRegi.getX();
		int col = (int) whereIsRegi.getY();
		
		
		
		if((row+1) < this.rows) {
			if (maze[row+1][col].AddRegi()) {
				moved = true;
				this.maze[row][col].setToBlank();
				whereIsRegi.setLocation(row+1, col);
				System.out.println("Now Regi is in: ("+ (row+1)+ ","+ (col)+")");
			}
		}else {
			System.out.println("Not possible to move down!");
		}
		
		return moved;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Program starts");
		boolean space = true; 
		Maze mymaze = new Maze(6,7);
		
	/*	THIS WAS A TEST TO CHECK IT WORKED
	 * while (space) {
			space = mymaze.moveToRight();
		}
		space = true;
		while (space) {
			space = mymaze.moveDown();
		}
		
		*/
		
		
	}
}
