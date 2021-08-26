package codeChallengeServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestWinCondition {

	@Test
	void test() {
		//unit test to check if the win condition method works
		BoardState board = new BoardState(6,9);
		board.makeTurn(1, "x");
		board.makeTurn(1, "x");
		board.makeTurn(1, "x");
		board.makeTurn(1, "x");
		board.makeTurn(1, "x");
		boolean win = false;
		win = board.checkWin();
		assertEquals(true, win);
	}

}
