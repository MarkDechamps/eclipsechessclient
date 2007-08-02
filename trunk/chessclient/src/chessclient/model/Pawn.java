package chessclient.model;

import java.util.Set;

public class Pawn extends Piece {

	Pawn(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"P");
	}

	@Override
	public Set<Square> getPossibleMoves() {
		return null;
	}

}
