package chessclient.model;

import java.util.Set;
import static chessclient.PieceImagePaths.*;

public class Rook extends Piece {

	Rook(Board board, Square location,boolean isWhite) {
		super(board, location,isWhite,"R");
		setImageName(isWhite?WROOK:BROOK);
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
