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
		return null;
	}

}
