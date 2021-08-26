package codeChallengeServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDrawCondition {

	@Test
	void test() {
		//unit test to check if the method to check if the game is drawn works
		BoardState board = new BoardState(6,9);
		board.fillGrid();
		System.out.println(board.toString());
		boolean draw = false;
		draw = board.checkDraw();
		assertEquals(true, draw);
	}

}
