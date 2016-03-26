package model;

/**
 * Enum CellState
 * @author Ye Li
 *
 */
public enum CellState {
	HUNTER("[O]"), WUMPUS("[W]"), BLOOD("[B]"), SLIME("[S]"), GOOP("[G]"), PIT("[P]"), NOTHING("[ ]"),HIDDEN("[X]");	
	
	private String state;
	
	CellState(String str){
		state = str;
	}
	
	public String getState() {
		return state;
	}
}

