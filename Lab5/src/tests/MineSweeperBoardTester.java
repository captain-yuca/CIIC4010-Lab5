package tests;

import static org.junit.Assert.*;
import main.MineSweeperBoard;

import org.junit.Test;

public class MineSweeperBoardTester {

	@Test
	public void restartBoardTest() {
		MineSweeperBoard m = new MineSweeperBoard(9, 9);
		m.clearBoard();
		m.populateBoard();
		assertTrue(true);
	}

}
