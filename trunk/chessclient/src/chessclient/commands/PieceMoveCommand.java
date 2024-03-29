package chessclient.commands;

import java.util.Set;

import org.eclipse.gef.commands.Command;

import chessclient.model.Piece;
import chessclient.model.Square;

public class PieceMoveCommand extends Command {
	Piece piece;
	Square oldSquare;
	Square newSquare;
	public PieceMoveCommand(Piece piece,Square destination) {
		super(piece.toString());
		this.piece = piece;
		this.oldSquare = piece.getSquare();
		this.newSquare = destination;
	}

	@Override
	public boolean canExecute() {
		if(oldSquare == newSquare){
			return false;
		}		
		Piece killedPiece = newSquare.getOccupier();
		if(killedPiece != null && (killedPiece.isWhite() == piece.isWhite())){
			// we can not take our own pieces
			return false;
		}
		
		Set<Square> moves = piece.getPossibleMoves();
		if(moves.contains(newSquare)){
			System.out.println("Piecemovecommand "+newSquare+" possible:"+moves);
			return true;
		}else
			return false;
	}
	@Override
	public void execute() {
		//System.out.println("Execute piecemovecommand");
		oldSquare.clear();
		piece.setSquare(newSquare);
		newSquare.setOccupier(piece);
	}
	
	@Override
	public void undo() {
		newSquare.clear();
		piece.setSquare(oldSquare);
		oldSquare.setOccupier(piece);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}
}
