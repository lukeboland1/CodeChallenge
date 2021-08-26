package codeChallengeServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestWinCondition {

	@Test
	void test() {
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
