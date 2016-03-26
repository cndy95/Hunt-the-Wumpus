package model;

import java.util.Observable;

/**
 * Map model for hunt the wumpus.
 * 
 * @author Ye Li
 *
 */

public class MapModel extends Observable {
	private CellState[][] cell;
	private CellState[][] cell1;
	private boolean[][] visible;

	private boolean inprogress;

	private boolean win;
	private int Hx;
	private int Hy;

	private int Wx;
	private int Wy;
	private int P1x;
	private int P1y;
	private int P2x;
	private int P2y;
	private int P3x;
	private int P3y;
	private int countp;
	private String message = "";

	/*
	 * construct exactly rooms
	 */
	public MapModel(CellState[][] t1) {
		cell = new CellState[10][10];
		cell1 = new CellState[10][10];
		visible = new boolean[10][10];
		inprogress = true;
		countp = 1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				visible[i][j] = false;
				cell1[i][j] = CellState.NOTHING;
				cell[i][j] = t1[i][j];

			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				cell[i][j] = t1[i][j];

				if (cell[i][j].getState() == "[O]") {
					Hx = i;
					Hy = j;
					visible[i][j] = true;
				} else if (cell[i][j].getState() == "[W]") {
					Wx = i;
					Wy = j;

				} else if (cell[i][j].getState() == "[P]") {
					if (countp == 1) {
						P1x = i;
						P1y = j;

					} else if (countp == 2) {
						P2x = i;
						P2y = j;
					} else {
						P3x = i;
						P3y = j;
					}
					countp = countp + 1;
				}
			}
		}

		// setUpState();

	}

	/*
	 * construct random rooms
	 */
	public MapModel() {
		inprogress = true;
		cell1 = new CellState[10][10];
		cell = new CellState[10][10];
		visible = new boolean[10][10];

		int[][] ranpos = new int[4][2];
		int[] array = new int[4];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				cell1[i][j] = CellState.NOTHING;
				cell[i][j] = CellState.NOTHING;
				visible[i][j] = false;
			}
		}

		for (int i = 0; i < 4; i++) {
			array[i] = (int) (Math.random() * 100);
			boolean duplicate = false;
			for (int j = 0; j < i; j++) {
				if (array[i] == array[j]) {
					duplicate = true;
					break;
				}
			}
			if (!duplicate) {
				ranpos[i][0] = array[i] / 10;
				ranpos[i][1] = array[i] % 10;
			} else
				i = i - 1;
		}
		Wx = ranpos[0][0];
		Wy = ranpos[0][1];
		P1x = ranpos[1][0];
		P1y = ranpos[1][1];
		P2x = ranpos[2][0];
		P2y = ranpos[2][1];
		P3x = ranpos[3][0];
		P3y = ranpos[3][1];
		setUpState();

	}

	/*
	 * save the cells' position,
	 */
	public void setUpState() {
		win = false;
		// generate the BLOOD;
		for (int rowIndex = Wx - 1; rowIndex <= Wx + 1; rowIndex++) {
			for (int colIndex = Wy - 1; colIndex <= Wy + 1; colIndex++) {
				if (!(rowIndex == Wx && colIndex == Wy)) {
					cell[(rowIndex + 10) % 10][(colIndex + 10) % 10] = CellState.BLOOD;
				}
			}
		}
		cell[(Wx + 2) % 10][Wy] = CellState.BLOOD;
		cell[(Wx + 8) % 10][Wy] = CellState.BLOOD;
		cell[Wx][(Wy + 2) % 10] = CellState.BLOOD;
		cell[Wx][(Wy + 8) % 10] = CellState.BLOOD;

		// generate the SlIME
		cell1[(P1x + 1) % 10][P1y] = CellState.SLIME;
		cell1[(P1x + 9) % 10][P1y] = CellState.SLIME;
		cell1[P1x][(P1y + 9) % 10] = CellState.SLIME;
		cell1[P1x][(P1y + 1) % 10] = CellState.SLIME;

		cell1[(P2x + 1) % 10][P2y] = CellState.SLIME;
		cell1[(P2x + 9) % 10][P2y] = CellState.SLIME;
		cell1[P2x][(P2y + 9) % 10] = CellState.SLIME;
		cell1[P2x][(P2y + 1) % 10] = CellState.SLIME;

		cell1[(P3x + 1) % 10][P3y] = CellState.SLIME;
		cell1[(P3x + 9) % 10][P3y] = CellState.SLIME;
		cell1[P3x][(P3y + 9) % 10] = CellState.SLIME;
		cell1[P3x][(P3y + 1) % 10] = CellState.SLIME;

		// generate the GOOP;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (cell1[i][j] == CellState.SLIME && cell[i][j] == CellState.BLOOD)
					cell[i][j] = CellState.GOOP;

				else if (cell1[i][j] == CellState.SLIME && cell[i][j] == CellState.WUMPUS)
					cell[i][j] = CellState.WUMPUS;

				else if (cell[i][j] == CellState.BLOOD && cell[i][j] == CellState.PIT)
					cell[i][j] = CellState.PIT;

				else if (cell[i][j] != CellState.BLOOD && cell1[i][j] == CellState.SLIME)
					cell[i][j] = cell1[i][j];
				else
					cell[i][j] = cell[i][j];
			}

		}

		// generate one HUNTER, one WUMPUS and three PIT

		cell[Wx][Wy] = CellState.WUMPUS;
		cell[P1x][P1y] = CellState.PIT;
		cell[P2x][P2y] = CellState.PIT;
		cell[P3x][P3y] = CellState.PIT;
		boolean Wpos = false;

		while (!Wpos) {
			int a = (int) (Math.random() * 100);
			if (cell[a % 10][a / 10] != CellState.BLOOD && cell[a % 10][a / 10] != CellState.SLIME
					&& cell[a % 10][a / 10] != CellState.PIT && cell[a % 10][a / 10] != CellState.GOOP
					&& cell[a % 10][a / 10] != CellState.WUMPUS) {
				Hx = a % 10;
				Hy = a / 10;
				Wpos = true;
			}
		}

		cell[Hx][Hy] = CellState.NOTHING;
		visible[Hx][Hy] = true;
		CheckSurrounding();

	}

	/*
	 * return win/lose or in progress
	 */
	public boolean getStatus() {
		return inprogress;
	}

	/*
	 * return is visible or invisible
	 */
	public boolean Isvisiable(int i, int j) {
		return visible[i][j];
	}

	/*
	 * hunter moves. 1 still nothing 2 close to Wumpus 3 close to Pit change the
	 * current location, make it visible.
	 */
	public void move(Direction d) {
		if (inprogress) {

			if (d == Direction.RIGHT) {
				Hy = (Hy + 1) % 10;
				visible[Hx][Hy] = true;

				CheckSurrounding();

			} else if (d == Direction.LEFT) {

				Hy = (Hy + 9) % 10;
				visible[Hx][Hy] = true;

				CheckSurrounding();

			} else if (d == Direction.UP) {

				Hx = (Hx + 9) % 10;
				visible[Hx][Hy] = true;

				CheckSurrounding();

			} else if (d == Direction.DOWN) {

				Hx = (Hx + 1) % 10;
				visible[Hx][Hy] = true;

				CheckSurrounding();

			}
			
			this.setChanged();
			notifyObservers(d);
		}
	}

	private void CheckSurrounding() {
		if (cell[Hx][Hy] == CellState.WUMPUS) {
			inprogress = false;
			win = false;
			message = "You were killed by the Wumpus, GAME OVER!";
			setChanged();
			notifyObservers();
		} else if (cell[Hx][Hy] == CellState.PIT) {
			inprogress = false;
			win = false;
			message = "You fell into a Pit, GAME OVER!";
			setChanged();
			notifyObservers();
		} else if (cell[Hx][Hy] == CellState.BLOOD) {
			inprogress = true;
			message = "The Wumpus is nearby, DANGEROUS!";
		} else if (cell[Hx][Hy] == CellState.SLIME) {
			inprogress = true;
			message = "You are in a Slime, DANGEROUS!";
		} else if (cell[Hx][Hy] == CellState.GOOP) {
			inprogress = true;
			message = "You are in a Goop! The Wumpus is nearby, DANGEROUS!";
		} else {
			inprogress = true;
			message = "currently safe haha";

		}

	}

	public void shoot(Direction d) {
		if (inprogress) {
			if (d == Direction.UP || d == Direction.DOWN) {
				for (int i = 0; i < 10; i++) {
					if (cell[i][Hy] == CellState.WUMPUS) {
						inprogress = false;
						win = true;
						message = "Shoot Wumpus! You Win!";
						setChanged();
						notifyObservers(d);
					}
				}
				if (inprogress) {
					inprogress = false;
					win = false;
					message = "Shoot Yourself! Game Over!";
					setChanged();
					notifyObservers(d);
				}
			} else if (d == Direction.LEFT || d == Direction.RIGHT) {
				for (int i = 0; i < 10; i++) {
					if (cell[Hx][i] == CellState.WUMPUS) {
						inprogress = false;
						win = true;
						message = "Shoot Wumpus! You Win!";
						setChanged();
						notifyObservers(d);
					}
				}
				if (inprogress) {
					inprogress = false;
					win = false;
					message = "Shoot Yourself! Game Over!";
					setChanged();
					notifyObservers(d);
				}

			}
		}
	}

	public boolean CheckWin() {
		return win;
	}

	/*
	 * return the exact cell's state, [X]when invisible, [OTHERS] when visible
	 */
	public Object getCellState(int i, int j) {
		if (!Isvisiable(i, j))
			return CellState.HIDDEN.getState();
		// else if (i == Hx && j == Hy)
		// return CellState.HUNTER.getState();
		else
			return cell[i][j].getState();
	}

	public Object getCellStateDone(int a, int b) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				visible[i][j] = true;
			}
		}
		// if (a == Hx && b == Hy)
		// return CellState.HUNTER.getState();
		// else
		return cell[a][b].getState();
	}

	public String getMessage() {
		return message;
	}

	public String toStringFirst(int a, int b) {
		String result = cell[a][b].getState().toString();

		return result;
	}

	public String toString() {
		String result = "";
		if (inprogress) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!Isvisiable(i, j))
						result = result + CellState.HIDDEN.getState() + " ";
					else if (i == Hx && j == Hy)
						result = result + CellState.HUNTER.getState() + " ";
					else
						result = result + cell[i][j].getState() + " ";
				}
				result = result + "\n";
				result = result + "\n";

			}
		} else
			result = showMap();
		return result;
	}

	public String showMap() {
		String result = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == Hx && j == Hy)
					result = result + CellState.HUNTER.getState() + " ";
				else {
					visible[i][j] = true;
					result = result + cell[i][j].getState() + " ";
				}
			}
			result = result + "\n";
			result = result + "\n";

		}
		return result;
	}

	public boolean isHunter(int a, int b) {
		return (a == Hx && b == Hy);
	}
	
	public boolean isWumpus(int a, int b) {
		return (a == Wx && b == Wy);
	}
	
	public boolean isPit(int a, int b) {
		if(a == P1x && b == P1y)
			return true;
		if (a == P2x && b == P2y)
			return true;
		if  (a == P3x && b == P3y)
			return true;
		else 
			return false;
	}
	

	public int getHunterX() {
		return Hx;
	}

	public int getHunterY() {
		return Hy;
	}

	public int getWumpusX() {
		return Wx;
	}

	public int getWumpusY() {
		return Wy;
	}

	public int getP1X() {
		return P1x;
	}

	public int getP1Y() {
		return P1y;
	}

	public int getP2X() {
		return P2x;
	}

	public int getP2Y() {
		return P2y;
	}

	public int getP3X() {
		return P3x;
	}

	public int getP3Y() {
		return P3y;
	}
}
