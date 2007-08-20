package chessclient.model;

import java.util.Set;
import static chessclient.PieceImagePaths.*;
public class Queen extends Piece {

	public Queen(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"Q");
		setImageName(isWhite?WQUEEN:BQUEEN);
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Board board = getBoard(); 
		Square square = getSquare();
		Set<Square> result = board.getSquaresAbove(square);
		result.addAll(board.getSquaresLeftOf(square));
		result.addAll(board.getSquaresRightOf(square));
		result.addAll(board.getSquaresUnder(square));
		result.addAll(board.getDiagonals(square));
		return result;
	}

}
