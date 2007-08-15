package chessclient.model;

import java.util.Set;
import java.util.TreeSet;

public class King extends Piece{

	King(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"K");

	}

	@Override
	public Set<Square> getPossibleMoves() {
		Set<Square> possibleMoves = new TreeSet();
		return possibleMoves;
	}

}
