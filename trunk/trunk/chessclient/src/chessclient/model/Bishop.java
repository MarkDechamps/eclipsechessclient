package chessclient.model;

import java.util.Set;

import static chessclient.PieceImagePaths.*;

public class Bishop extends Piece {

	Bishop(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"B");
		setImageName(isWhite?WBISHOP:BBISHOP);
		
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Board board = getBoard(); 
		Square square = getSquare();
		Set<Square> result = board.getDiagonals(square);
		return result;
	}

}
