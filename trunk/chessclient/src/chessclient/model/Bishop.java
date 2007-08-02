package chessclient.model;

import java.util.Set;

public class Bishop extends Piece {

	Bishop(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"B");
		
	}

	@Override
	public Set<Square> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
