package chessclient.model;

import java.util.Set;

public class Queen extends Piece {

	public Queen(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"Q");
	}

	@Override
	public Set<Square> getPossibleMoves() {
		return null;
	}

}
