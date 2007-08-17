package chessclient.model;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
	
	
	Pawn(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"P");
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Set<Square> result = new HashSet<Square>();
		Board board = getBoard();
		Square current = getSquare();
		int number = current.getNumber();
		Set <Square> above = board.getSquaresAbove(current);
		System.out.println("squares above this pawn:"+above);
		// can we move one?
		Square oneInFront = Board.int2Square(board, number+1);
		if(above.contains(oneInFront) && canGoTo(oneInFront)){
			result.add(oneInFront);
		}
		
		// is it the first move for this pawn ?
		if(current == getStartSquare()){
			Square s = Board.int2Square(board, number+2);
			if(above.contains(s) && canGoTo(s)){
				result.add(s);
			}
		}
		return result;
	}

	private boolean canGoTo(Square square){
		/* same square?*/
		if(square == getSquare()){
			return true;
		}
		/* empty square ?*/
		if(square.isEmpty()){
			return true;
		}
		
		/* an enemy piece ?*/
		if(square.getOccupier() != null  && square.getOccupier().isWhite() == isWhite()){
			return true;
		}

		return false;
	}
}
