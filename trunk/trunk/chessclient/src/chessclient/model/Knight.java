package chessclient.model;

import java.util.Set;

public class Knight extends Piece {

	Knight(Board board, Square location,boolean isWhite) {
		super(board, location,isWhite,"N");
	}

	@Override
	public Set<Square> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
