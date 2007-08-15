package chessclient.model;

import java.util.Set;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public abstract class Piece {
	private boolean isWhite;

	private Board board;

	private Square square;

	private String notation = "";

	/* information for the graphical location/size */
	private Point location;

	private Dimension size;

	/**
	 * the piece needs the board to determine its possible moves
	 */
	Piece(Board board, Square square, boolean isWhite, String notation) {
		this.board = board;
		this.square = square;
		this.isWhite = isWhite;
		this.notation = notation;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension dimension) {
		this.size = dimension;
	}

	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Point getLocation() {
		return location;
	}

	public Square getSquare() {
		return square;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	/*
	 * move to that square if possible , return false if not possible Ask board
	 * for check after a valid move is made, if it is check then undo the move
	 */

	public boolean moveTo(Square destinationSquare) {
		Set<Square> possibleMoves = getPossibleMoves();
		if (!possibleMoves.contains(destinationSquare)) {
			return false;
		}

		/* make move */
		Square previousSquare = square;
		square.clear();
		square = destinationSquare;
		square.setOccupier(this);

		/* check for check */
		if (board.isCheck()) {
			/* if check undo move and return false else return true */
			square.clear();
			square = previousSquare;
			square.setOccupier(this);
			return false;
		}
		/* since we got here, it must be a legal move */
		return true;
	}

	public abstract Set<Square> getPossibleMoves();

	public String toString() {
		return isWhite() ? notation.toUpperCase() : notation.toLowerCase();
	}

}
