package chessclient.model;

import java.util.Set;

public abstract class Piece {
	private boolean isWhite;

	private Board board;

	private Square location;
	private String notation ="";
	/**
	 * the piece needs the board to determine its possible moves
	 */
	Piece(Board board, Square location, boolean isWhite,String notation) {
		this.board = board;
		this.location = location;
		this.isWhite = isWhite;
		this.notation = notation;
	}

	public Square getLocation() {
		return location;
	}

	public Square getSquare(){
		return getLocation();
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

	public boolean moveTo(Square square) {
		Set<Square> possibleMoves = getPossibleMoves();
		if (!possibleMoves.contains(square)) {
			return false;
		}

		/* make move */
		Square locationBackup = location;
		location.clear();
		location = square;
		location.setOccupier(this);

		/* check for check */
		if (board.isCheck()) {
			/* if check undo move and return false else return true */
			location.clear();
			location = locationBackup;
			location.setOccupier(this);
			return false;
		}
		/* since we got here, it must be a legal move */
		return true;
	}

	public abstract Set<Square> getPossibleMoves();
	public String toString(){
		return isWhite()?notation.toUpperCase():notation.toLowerCase();
	}
}
