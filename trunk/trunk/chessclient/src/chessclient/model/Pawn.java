package chessclient.model;

import java.util.HashSet;
import java.util.Set;
import static chessclient.PieceImagePaths.*;

public class Pawn extends Piece {
	
	
	Pawn(Board board, Square location, boolean isWhite) {
		super(board, location, isWhite,"P");
		setImageName(isWhite?WPAWN:BPAWN);
	}

	@Override
	public Set<Square> getPossibleMoves() {
		Set<Square> result = new HashSet<Square>();
		Board board = getBoard();
		Square current = getSquare();
		int number = current.getNumber();
		Set <Square> above = null;
		try{
		 above = board.getSquaresAbove(current);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// can we move one?
		Square oneInFront = Board.int2Square(board, number+1);
		System.out.println("oneinfront is:"+oneInFront);
		if(above.contains(oneInFront) && canGoTo(oneInFront)){
			result.add(oneInFront);
		}
		
		// is it the first move for this pawn ?
		if(current == getStartSquare()){
			// can we move 2 :) ?
			Square s = Board.int2Square(board, number+2);
			if(above.contains(s) && canGoTo(s)){
				result.add(s);
			}
		}
		return result;
	}

	
}
