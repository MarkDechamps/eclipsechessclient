package chessclient.model;

import java.util.Set;
import static chessclient.PieceImagePaths.*;
public class Knight extends Piece {

	Knight(Board board, Square location,boolean isWhite) {
		super(board, location,isWhite,"N");
		setImageName(isWhite?WKNIGHT:BKNIGHT);
	}

	@Override
	public Set<Square> getPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
