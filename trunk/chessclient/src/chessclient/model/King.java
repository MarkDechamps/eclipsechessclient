package chessclient.model;

import java.util.Set;
import java.util.TreeSet;
import static chessclient.PieceImagePaths.*;

public class King extends Piece{

	King(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"K");
		setImageName(isWhite?WKING:BKING);
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Set<Square> possibleMoves = new TreeSet();
		return possibleMoves;
	}

}
