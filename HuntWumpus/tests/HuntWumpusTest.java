package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.CellState;
import model.Direction;
import model.MapModel;

/**
 * Test cases for Hunt the Wumpus
 * 
 * @author yeeeeli
 *
 */
public class HuntWumpusTest {
	/*
	 * test killed by HUMPUS
	 */
	@Test
	public void test1() {
		CellState[][] t1 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t1[i][j] = CellState.NOTHING;
			}
		}
		t1[0][0] = CellState.HUNTER;

		t1[0][1] = CellState.BLOOD;
		t1[0][2] = CellState.BLOOD;
		t1[0][3] = CellState.BLOOD;
		t1[0][6] = CellState.SLIME;

		t1[1][0] = CellState.BLOOD;
		t1[1][1] = CellState.BLOOD;
		t1[1][2] = CellState.WUMPUS;
		t1[1][3] = CellState.BLOOD;
		t1[1][4] = CellState.BLOOD;
		t1[1][5] = CellState.SLIME;
		t1[1][6] = CellState.PIT;
		t1[1][7] = CellState.SLIME;
		t1[1][9] = CellState.SLIME;

		t1[2][0] = CellState.SLIME;
		t1[2][1] = CellState.BLOOD;
		t1[2][2] = CellState.BLOOD;
		t1[2][3] = CellState.BLOOD;
		t1[2][6] = CellState.SLIME;
		t1[2][8] = CellState.SLIME;
		t1[2][9] = CellState.PIT;

		t1[5][7] = CellState.SLIME;

		t1[6][6] = CellState.SLIME;
		t1[6][7] = CellState.PIT;
		t1[6][8] = CellState.SLIME;

		t1[7][7] = CellState.SLIME;
		t1[9][2] = CellState.SLIME;

		MapModel T1 = new MapModel(t1);
		assertTrue(T1.Isvisiable(0, 0));
		assertFalse(T1.Isvisiable(3, 4));
		assertFalse(T1.Isvisiable(5, 5));
		assertFalse(T1.Isvisiable(9, 9));

		assertEquals("[O]", T1.getCellState(0, 0));
		assertEquals("[X]", T1.getCellState(0, 1));
		assertEquals("[X]", T1.getCellState(0, 6));
		assertEquals("[X]", T1.getCellState(1, 2));
		assertEquals("[X]", T1.getCellState(1, 6));
		assertEquals("[X]", T1.getCellState(9, 9));

		assertEquals("[O]", T1.getCellStateDone(0, 0));
		assertEquals("[B]", T1.getCellStateDone(0, 1));
		assertEquals("[S]", T1.getCellStateDone(0, 6));
		assertEquals("[W]", T1.getCellStateDone(1, 2));
		assertEquals("[P]", T1.getCellStateDone(1, 6));
		assertEquals("[ ]", T1.getCellStateDone(9, 9));
		assertTrue(T1.getStatus());

		T1.move(Direction.RIGHT);
		assertTrue(T1.Isvisiable(0, 0));
		assertTrue(T1.Isvisiable(0, 1));
		assertTrue(T1.isHunter(0, 1));
		assertEquals("The Wumpus is nearby, DANGEROUS!", T1.getMessage());
		assertTrue(T1.getStatus());

		T1.move(Direction.DOWN);
		assertEquals("[B]", T1.getCellState(0, 1));
		assertTrue(T1.Isvisiable(1, 1));
		assertTrue(T1.isHunter(1, 1));

		assertTrue(T1.getStatus());

		T1.move(Direction.RIGHT);
		assertEquals("[B]", T1.getCellState(1, 1));
		assertTrue(T1.Isvisiable(1, 2));

		T1.move(Direction.DOWN);
		assertEquals("You were killed by the Wumpus, GAME OVER!", T1.getMessage());
		assertFalse(T1.getStatus());

		assertFalse(T1.CheckWin());

	}

	/*
	 * test killed by HIT
	 */
	@Test
	public void test2() {
		CellState[][] t1 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t1[i][j] = CellState.NOTHING;
			}
		}
		t1[0][9] = CellState.HUNTER;

		t1[0][1] = CellState.BLOOD;
		t1[0][2] = CellState.BLOOD;
		t1[0][3] = CellState.BLOOD;
		t1[0][6] = CellState.SLIME;

		t1[1][0] = CellState.BLOOD;
		t1[1][1] = CellState.BLOOD;
		t1[1][2] = CellState.WUMPUS;
		t1[1][3] = CellState.BLOOD;
		t1[1][4] = CellState.BLOOD;
		t1[1][5] = CellState.SLIME;
		t1[1][6] = CellState.PIT;
		t1[1][7] = CellState.SLIME;
		t1[1][9] = CellState.SLIME;

		t1[2][0] = CellState.SLIME;
		t1[2][1] = CellState.BLOOD;
		t1[2][2] = CellState.BLOOD;
		t1[2][3] = CellState.BLOOD;
		t1[2][6] = CellState.SLIME;
		t1[2][8] = CellState.SLIME;
		t1[2][9] = CellState.PIT;

		t1[5][7] = CellState.SLIME;

		t1[6][6] = CellState.SLIME;
		t1[6][7] = CellState.PIT;
		t1[6][8] = CellState.SLIME;

		t1[7][7] = CellState.SLIME;
		t1[9][2] = CellState.SLIME;

		MapModel T1 = new MapModel(t1);
		assertTrue(T1.Isvisiable(0, 9));
		assertFalse(T1.Isvisiable(3, 4));
		assertFalse(T1.Isvisiable(5, 5));
		assertFalse(T1.Isvisiable(9, 9));

		assertEquals("[X]", T1.getCellState(0, 1));
		assertEquals("[X]", T1.getCellState(0, 6));
		assertEquals("[X]", T1.getCellState(1, 2));
		assertEquals("[X]", T1.getCellState(1, 6));
		assertEquals("[O]", T1.getCellState(0, 9));

		T1.move(Direction.DOWN);
		assertTrue(T1.Isvisiable(1, 9));
		assertTrue(T1.isHunter(1, 9));

		assertEquals("You are in a Slime, DANGEROUS!", T1.getMessage());

		T1.move(Direction.DOWN);
		assertEquals("[S]", T1.getCellState(1, 9));

		assertTrue(T1.Isvisiable(2, 9));
		assertTrue(T1.isHunter(2, 9));

		assertEquals("You fell into a Pit, GAME OVER!", T1.getMessage());
		assertFalse(T1.CheckWin());
	}

	/*
	 * test killed by HIT
	 */
	@Test
	public void test3() {
		/*
		 * [ ][S][P][S][ ][ ][ ][ ][ ][ ] [ ][ ][S][ ][ ][ ][ ][ ][ ][ ] [ ][ ][
		 * ][ ][ ][ ][ ][ ][ ][ ] [ ][ ][ ][S][O][B][ ][ ][ ][ ] [ ][
		 * ][S][P][G][B][B][ ][ ][ ] [ ][ ][ ][G][B][W][B][B][ ][ ] [ ][ ][ ][
		 * ][B][B][B][ ][ ][ ] [ ][ ][ ][ ][ ][B][ ][ ][ ][ ] [ ][ ][ ][ ][ ][
		 * ][ ][ ][ ][ ] [ ][ ][S][ ][ ][ ][ ][ ][ ][ ]
		 */

		CellState[][] t3 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t3[i][j] = CellState.NOTHING;
			}
		}
		t3[0][1] = CellState.SLIME;
		t3[0][2] = CellState.PIT;
		t3[0][3] = CellState.SLIME;

		t3[1][2] = CellState.SLIME;

		t3[3][4] = CellState.SLIME;
		t3[3][6] = CellState.BLOOD;

		t3[4][2] = CellState.SLIME;
		t3[4][3] = CellState.PIT;
		t3[4][4] = CellState.GOOP;
		t3[4][5] = CellState.BLOOD;
		t3[4][6] = CellState.BLOOD;

		t3[5][3] = CellState.GOOP;
		t3[5][4] = CellState.BLOOD;
		t3[5][5] = CellState.WUMPUS;
		t3[5][6] = CellState.BLOOD;
		t3[5][7] = CellState.BLOOD;

		t3[6][4] = CellState.BLOOD;
		t3[6][5] = CellState.BLOOD;
		t3[6][6] = CellState.BLOOD;

		t3[7][5] = CellState.BLOOD;

		t3[9][2] = CellState.SLIME;
		t3[3][4] = CellState.HUNTER;

		MapModel T3 = new MapModel(t3);
		assertTrue(T3.Isvisiable(3, 4));
		assertFalse(T3.Isvisiable(3, 8));
		assertFalse(T3.Isvisiable(5, 5));
		assertFalse(T3.Isvisiable(9, 9));
		assertEquals("[X]", T3.getCellState(0, 1));
		assertEquals("[X]", T3.getCellState(0, 6));
		assertEquals("[X]", T3.getCellState(1, 2));
		assertEquals("[X]", T3.getCellState(1, 6));
		assertEquals("[O]", T3.getCellState(3, 4));

		T3.move(Direction.DOWN);
		assertTrue(T3.Isvisiable(4, 4));
		assertTrue(T3.isHunter(4, 4));

		assertEquals("You are in a Goop! The Wumpus is nearby, DANGEROUS!", T3.getMessage());

		T3.move(Direction.LEFT);
		assertEquals("[G]", T3.getCellState(4, 4));

		assertTrue(T3.Isvisiable(4, 3));
		assertTrue(T3.isHunter(4, 3));
		assertEquals("You fell into a Pit, GAME OVER!", T3.getMessage());
		assertFalse(T3.CheckWin());

	}

	/*
	 * test killed by shoot oneself
	 */
	@Test
	public void test4() {
		/*
		 * 0 1 2 3 4 5 6 7 8 9 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 1[ ][ ][ ][ ][
		 * ][B][ ][ ][ ][ ] 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 3[S][ ][ ][ ][ ][ ][
		 * ][ ][ ][ ] 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 5[S][S][P][S][ ][ ][ ][ ][
		 * ][ ] 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][ ][B][B][B][ ][ ][ ] 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */

		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[2][5] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(2, 5));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(2, 5));

		T4.shoot(Direction.LEFT);
		assertFalse(T4.CheckWin());
		assertEquals(T4.getMessage(), "Shoot Yourself! Game Over!");
	}

	/*
	 * test win by shoot down
	 */
	@Test
	public void test5() {
		/*
		 * 0 1 2 3 4 5 6 7 8 9 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 1[ ][ ][ ][ ][
		 * ][B][ ][ ][ ][ ] 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 3[S][ ][ ][ ][ ][ ][
		 * ][ ][ ][ ] 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 5[S][S][P][S][ ][ ][ ][ ][
		 * ][ ] 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][ ][B][B][B][ ][ ][ ] 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */

		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[6][5] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(6, 5));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(6, 5));

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(7, 5));
		assertTrue(T4.isHunter(7, 5));

		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(8, 5));
		assertEquals("[B]", T4.getCellState(7, 5));

		assertTrue(T4.isHunter(8, 5));
		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());
		T4.shoot(Direction.DOWN);
		assertEquals("Shoot Wumpus! You Win!", T4.getMessage());

		assertTrue(T4.CheckWin());
	}

	/*
	 * test win by shoot right
	 */
	@Test
	public void test6() {
		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[8][3] = CellState.HUNTER;

		MapModel T5 = new MapModel(t4);
		assertTrue(T5.Isvisiable(8, 3));
		assertFalse(T5.Isvisiable(3, 8));
		assertFalse(T5.Isvisiable(5, 5));
		assertFalse(T5.Isvisiable(9, 9));
		assertEquals("[X]", T5.getCellState(0, 1));
		assertEquals("[X]", T5.getCellState(0, 6));
		assertEquals("[X]", T5.getCellState(1, 2));
		assertEquals("[X]", T5.getCellState(1, 6));
		assertTrue(T5.isHunter(8, 3));

		T5.move(Direction.DOWN);
		assertTrue(T5.Isvisiable(9, 3));
		assertTrue(T5.isHunter(9, 3));
		assertEquals("The Wumpus is nearby, DANGEROUS!", T5.getMessage());

		T5.shoot(Direction.RIGHT);
		assertEquals("Shoot Wumpus! You Win!", T5.getMessage());

		assertTrue(T5.CheckWin());

		/*
		 *    0 1  2  3  4  5  6  7  8  9 
		 * 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 
		 * 1[ ][ ][ ][ ][ ][B][ ][ ][ ][ ] 
		 * 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 3[S][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 
		 * 5[S][S][P][S][ ][ ][ ][ ][ ][ ] 
		 * 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 
		 * 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][O][B][B][B][ ][ ][ ] 
		 * 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */

	}

	/*
	 * test win by shoot left
	 */
	@Test
	public void test7() {

		/*
		 *    0 1  2  3  4  5  6  7  8  9 
		 * 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 
		 * 1[ ][ ][ ][ ][ ][B][ ][ ][ ][ ] 
		 * 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 3[S][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 
		 * 5[S][S][P][S][ ][ ][ ][ ][ ][ ] 
		 * 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 
		 * 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][O][B][B][B][ ][ ][ ] 
		 * 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */


		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[6][5] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(6, 5));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(6, 5));

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(7, 5));
		assertTrue(T4.isHunter(7, 5));
		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(8, 5));
		assertEquals("[B]", T4.getCellState(7, 5));
		assertTrue(T4.isHunter(8, 5));

		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());
		T4.shoot(Direction.UP);
		assertTrue(T4.CheckWin());
	
	}

	/*
	 * test win by shoot up
	 */
	@Test
	public void test8() {

		/*
		 *    0 1  2  3  4  5  6  7  8  9 
		 * 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 
		 * 1[ ][ ][ ][ ][ ][B][ ][ ][ ][ ] 
		 * 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 3[S][ ][ ][ ][ ][ ][ ][ ][ ][ ] 
		 * 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 
		 * 5[S][S][P][S][ ][ ][ ][ ][ ][ ] 
		 * 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 
		 * 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][O][B][B][B][ ][ ][ ] 
		 * 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */


		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[6][5] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(6, 5));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(6, 5));

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(7, 5));
		assertTrue(T4.isHunter(7, 5));
		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());

		T4.move(Direction.DOWN);
		assertTrue(T4.Isvisiable(8, 5));
		assertEquals("[B]", T4.getCellState(7, 5));
		assertTrue(T4.isHunter(8, 5));

		assertEquals("The Wumpus is nearby, DANGEROUS!", T4.getMessage());
		T4.shoot(Direction.UP);
		assertTrue(T4.CheckWin());
	}

	/*
	 * test killed by shoot oneself
	 */
	@Test
	public void test9() {
		/*
		 * 0 1 2 3 4 5 6 7 8 9 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 1[ ][ ][ ][ ][
		 * ][B][ ][ ][ ][ ] 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 3[S][ ][ ][ ][ ][ ][
		 * ][ ][ ][ ] 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 5[S][S][P][S][ ][ ][ ][ ][
		 * ][ ] 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][ ][B][B][B][ ][ ][ ] 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */

		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[3][1] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(3, 1));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(3, 1));
		T4.move(Direction.UP);

		T4.shoot(Direction.RIGHT);
		assertFalse(T4.CheckWin());
	}

	/*
	 * test killed by shoot oneself
	 */
	@Test
	public void test10() {
		/*
		 * 0 1 2 3 4 5 6 7 8 9 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 1[ ][ ][ ][ ][
		 * ][B][ ][ ][ ][ ] 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 3[S][ ][ ][ ][ ][ ][
		 * ][ ][ ][ ] 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 5[S][S][P][S][ ][ ][ ][ ][
		 * ][ ] 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][ ][B][B][B][ ][ ][ ] 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */

		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[9][8] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertTrue(T4.Isvisiable(9, 8));
		assertFalse(T4.Isvisiable(3, 8));
		assertFalse(T4.Isvisiable(5, 5));
		assertFalse(T4.Isvisiable(9, 9));
		assertEquals("[X]", T4.getCellState(0, 1));
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(1, 2));
		assertEquals("[X]", T4.getCellState(1, 6));
		assertEquals("[O]", T4.getCellState(9, 8));

		T4.shoot(Direction.UP);
		assertFalse(T4.CheckWin());
	}

	@Test
	public void testToString() {
		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[9][8] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertEquals("[X]", T4.getCellState(0, 6));
		assertEquals("[X]", T4.getCellState(0, 9));
		assertEquals("[X]", T4.getCellState(2, 6));
		assertEquals("[X]", T4.getCellState(5, 6));
		assertEquals("[X]", T4.getCellState(7, 6));
		assertEquals("[X]", T4.getCellState(3, 5));

		assertEquals(T4.toString(), ("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [O] [X] \n\n"));
		/*
		 * 0 1 2 3 4 5 6 7 8 9 0[ ][ ][ ][ ][B][B][B][ ][ ][ ] 1[ ][ ][ ][ ][
		 * ][B][ ][ ][ ][ ] 2[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ] 3[S][ ][ ][ ][ ][ ][
		 * ][ ][ ][ ] 4[P][S][S][ ][ ][ ][ ][ ][ ][S] 5[S][S][P][S][ ][ ][ ][ ][
		 * ][ ] 6[ ][ ][S][P][S][ ][ ][ ][ ][ ] 7[ ][ ][ ][S][ ][B][ ][ ][ ][ ]
		 * 8[ ][ ][ ][ ][B][B][B][ ][ ][ ] 9[ ][ ][ ][B][B][W][B][B][ ][ ]
		 */
		T4.shoot(Direction.UP);
		assertEquals(T4.toString(), ("[ ] [ ] [ ] [ ] [B] [B] [B] [ ] [ ] [ ] \n\n"
				+ "[ ] [ ] [ ] [ ] [ ] [B] [ ] [ ] [ ] [ ] \n\n" + "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] \n\n"
				+ "[S] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] \n\n" + "[P] [S] [S] [ ] [ ] [ ] [ ] [ ] [ ] [S] \n\n"
				+ "[S] [S] [P] [S] [ ] [ ] [ ] [ ] [ ] [ ] \n\n" + "[ ] [ ] [S] [P] [S] [ ] [ ] [ ] [ ] [ ] \n\n"
				+ "[ ] [ ] [ ] [S] [ ] [B] [ ] [ ] [ ] [ ] \n\n" + "[ ] [ ] [ ] [ ] [B] [B] [B] [ ] [ ] [ ] \n\n"
				+ "[ ] [ ] [ ] [B] [B] [W] [B] [B] [O] [ ] \n\n"));
	}

	@Test
	public void test13() {
		CellState[][] t4 = new CellState[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				t4[i][j] = CellState.NOTHING;
			}
		}
		t4[0][4] = CellState.BLOOD;
		t4[0][5] = CellState.BLOOD;
		t4[0][6] = CellState.BLOOD;

		t4[1][5] = CellState.BLOOD;

		t4[3][0] = CellState.SLIME;

		t4[4][0] = CellState.PIT;
		t4[4][1] = CellState.SLIME;
		t4[4][2] = CellState.SLIME;
		t4[4][9] = CellState.SLIME;

		t4[5][0] = CellState.SLIME;
		t4[5][1] = CellState.SLIME;
		t4[5][2] = CellState.PIT;
		t4[5][3] = CellState.SLIME;

		t4[6][2] = CellState.SLIME;
		t4[6][3] = CellState.PIT;
		t4[6][4] = CellState.SLIME;

		t4[7][3] = CellState.SLIME;
		t4[7][5] = CellState.BLOOD;

		t4[8][4] = CellState.BLOOD;
		t4[8][5] = CellState.BLOOD;
		t4[8][6] = CellState.BLOOD;

		t4[9][3] = CellState.BLOOD;
		t4[9][4] = CellState.BLOOD;
		t4[9][5] = CellState.WUMPUS;
		t4[9][6] = CellState.BLOOD;
		t4[9][7] = CellState.BLOOD;

		t4[9][8] = CellState.HUNTER;

		MapModel T4 = new MapModel(t4);
		assertEquals("[B]", T4.toStringFirst(0, 4));
	}

	@Test
	public void testRandom() {

		MapModel T4 = new MapModel();
		int hx = T4.getHunterX();
		int hy = T4.getHunterY();
		int wx = T4.getWumpusX();
		int wy = T4.getWumpusY();
		int p1x = T4.getP1X();
		int p1y = T4.getP1Y();
		int p2x = T4.getP2X();
		int p2y = T4.getP2Y();
		int p3x = T4.getP3X();
		int p3y = T4.getP3Y();

		assertFalse(hx == wx && hy == wy);
		assertFalse(hx == wx-1 && hy == wy);
		assertFalse(hx == wx-2 && hy == wy);
		assertFalse(hx == wx+1 && hy == wy);
		assertFalse(hx == wx+2 && hy == wy);
		
		assertFalse(hx == wx && hy == wy-1);
		assertFalse(hx == wx && hy == wy-2);
		assertFalse(hx == wx && hy == wy+1);
		assertFalse(hx == wx && hy == wy+2);
		
		assertFalse(hx == wx-1 && hy == wy-1);
		assertFalse(hx == wx-1 && hy == wy+1);
		assertFalse(hx == wx && hy == wy-1);
		assertFalse(hx == wx && hy == wy+1);

		assertFalse(hx == p1x && hy == p1y);
		assertFalse(hx == p1x-1 && hy == p1y);
		assertFalse(hx == p1x+1 && hy == p1y);
		assertFalse(hx == p1x && hy == p1y+1);
		assertFalse(hx == p1x && hy == p1y-1);
		
		assertFalse(hx == p2x && hy == p2y);
		assertFalse(hx == p2x-1 && hy == p2y);
		assertFalse(hx == p2x+1 && hy == p2y);
		assertFalse(hx == p2x && hy == p2y+1);
		assertFalse(hx == p2x && hy == p2y-1);
		
		assertFalse(hx == p3x && hy == p3y);
		assertFalse(hx == p3x-1 && hy == p3y);
		assertFalse(hx == p3x+1 && hy == p3y);
		assertFalse(hx == p3x && hy == p3y+1);
		assertFalse(hx == p3x && hy == p3y-1);
			
		assertFalse(hx == p1x && hy == p1y);
		assertFalse(hx == p2x && hy == p2y);
		assertFalse(hx == p3x && hy == p3y);

		assertFalse(hx == p2x && hy == p2y);
		assertFalse(hx == p3x && hy == p3y);

		assertFalse(hx == p3x && hy == p3y);
		T4.isPit(p1x, p1y);
		T4.isPit(p2x, p3y);
		T4.isPit(p3x, p3y);
		
		T4.isWumpus(wx, wy);

		assertEquals("[ ]", T4.toStringFirst(hx, hy));
		assertEquals("[W]", T4.toStringFirst(wx, wy));
		assertEquals("[P]", T4.toStringFirst(p1x, p1y));
		assertEquals("[P]", T4.toStringFirst(p2x, p2y));
		assertEquals("[P]", T4.toStringFirst(p3x, p3y));

	}
}
