package chessclient.model.tests;

import chessclient.model.Board;
import junit.framework.TestCase;

public class BoardTest extends TestCase {

	public void testPrintBoard() {
		Board board = new Board();
		board.resetBoard();
		board.printBoard();
	}

}
