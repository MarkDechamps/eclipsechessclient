package chessclient.model;

import java.util.Set;

public class King extends Piece{

	King(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"K");

	}

	@Override
	public Set<Square> getPossibleMoves() {
		// TODO Auto-generated constructor stub
		return null;
	}

}
