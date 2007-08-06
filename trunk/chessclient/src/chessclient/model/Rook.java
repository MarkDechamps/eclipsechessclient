package chessclient.model;

import java.util.Set;

public class Rook extends Piece {

	Rook(Board board, Square location,boolean isWhite) {
		super(board, location,isWhite,"R");
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Board board = getBoard(); 
		Square square = getSquare();
		Set<Square> result = board.getSquaresAbove(square);
		result.addAll(board.getSquaresLeftOf(square));
		result.addAll(board.getSquaresRightOf(square));
		result.addAll(board.getSquaresUnder(square));
		return result;
	}
}
