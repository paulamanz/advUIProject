package programmingproject.maze;

public class Cell {
	
	private CellObject state;
	
	public Cell() {
	}
	
	public Cell(CellObject state) {
		this.state = state;
	}
	
	public CellObject getState() {
		return state;
	}

	public void setState(CellObject state) {
		this.state = state;
	}

	public boolean AddRegi() {
		
		if(state != CellObject.OBSTACLE) {
			this.state = CellObject.REGI;
			System.out.println("Regi MOVED!");
			return true;
		}else {
			System.out.println("Not possible to move Regi :(");
			return false;
		}
		
	}
	
	public void addObstacle() {
		this.state = CellObject.OBSTACLE;
	}
	
	public void addTreasure() {
		this.state = CellObject.TREASURE;
	}
	
	public void setToBlank () {
		this.state = CellObject.BLANK;
	}
	
	public boolean isRegi() {
		if (state == CellObject.REGI) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isObstacle() {
		if (state == CellObject.OBSTACLE) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTreasure() {
		if (state == CellObject.TREASURE) {
			return true;
		}else {
			return false;
		}
	}
}
