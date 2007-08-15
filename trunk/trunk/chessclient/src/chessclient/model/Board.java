package chessclient.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import chessclient.enums.ColorPlayer;
import chessclient.enums.GameResult;
import chessclient.enums.Pieces;
import chessclient.enums.Reason;
import chessclient.enums.SquareState;

/* this is the root of the model
 * note: ! if the model changes, you MUST call 
 * setChanged() and notifyAll() otherwise the drawing will not be
 * updated
 * 
 *
 * */

public class Board extends Observable {

	public static int MAXHORIZONTAL = 8;

	public static int MAXVERTICAL = 8;

	Map<String, Square> squares = new HashMap<String, Square>();

	private Pieces promotionPiece;

	private Reason reason;

	private ColorPlayer turn;

	private GameResult gameResult;

	private Dimension size;

	private Point location;

	public static Square int2Square(Board board, int fieldNumber) {
		int v = fieldNumber % 10;
		int h = fieldNumber / 10;
		return board.getSquares().get("" + h + v);
	}

	public Board() {
		
		location = new Point(0,0);
		size = new Dimension(500,500);
		/* create the board */
		for (int h = 0; h < MAXHORIZONTAL; h++) {
			for (int v = 0; v < MAXVERTICAL; v++) {
				String s = "" + (char) ('a' + h) + (v + 1);
				// System.out.println(s);
				boolean isWhite = (h + v) % 2 == 1;
				int number = h * 10 + v;
				Square square = new Square(isWhite, s, number);
				squares.put(s, square);
			}
		}
		/* put the pieces on */
		resetBoard();
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int v = MAXVERTICAL-1; v>-1; v--) {
			for (int h = 0; h < MAXHORIZONTAL; h++) {
				String s = "" + (char) ('a' + h) + (v + 1);
				Square square = squares.get(s);
				Piece p = square.getOccupier();
				if (square.getIsWhite()) {
					result.append("[ ");
					if (p == null) {
						result.append(".");
					} else
						result.append(p.toString());
					result.append(" ]");
				} else {
					result.append("[*");
					if (p == null) {
						result.append(".");
					} else
						result.append(p.toString());
					result.append("*]");
				}
			}
			result.append("\n");
		}
		return result.toString();
	}

	public void resetBoard() {
		for (int h = 0; h < MAXHORIZONTAL; h++) {
			for (int v = 0; v < MAXVERTICAL; v++) {
				String s = "" + (char) ('a' + h) + (v + 1);
				Square square = squares.get(s);				
				square.clear();
			}
		}
		/* Put the pieces on the board */
		List<String> sq = new ArrayList<String>();
		sq.add("a1");
		sq.add("h1");
		putPiece(Pieces.Rook, sq, true);
		sq.clear();
		sq.add("a8");
		sq.add("h8");
		putPiece(Pieces.Rook, sq, false);
		sq.clear();
		sq.add("b1");
		sq.add("g1");
		putPiece(Pieces.Knight, sq, true);
		sq.clear();
		sq.add("b8");
		sq.add("g8");
		putPiece(Pieces.Knight, sq, false);
		sq.clear();
		sq.add("c1");
		sq.add("f1");
		putPiece(Pieces.Bishop, sq, true);
		sq.clear();
		sq.add("c8");
		sq.add("f8");
		putPiece(Pieces.Bishop, sq, false);
		sq.clear();
		sq.add("d1");
		putPiece(Pieces.Queen, sq, true);
		sq.clear();
		sq.add("d8");
		putPiece(Pieces.Queen, sq, false);
		sq.clear();
		sq.add("e1");
		putPiece(Pieces.King, sq, true);
		sq.clear();
		sq.add("e8");
		putPiece(Pieces.King, sq, false);
		sq.clear();
		sq.add("a2");
		sq.add("b2");
		sq.add("c2");
		sq.add("d2");
		sq.add("e2");
		sq.add("f2");
		sq.add("g2");
		sq.add("h2");
		putPiece(Pieces.Pawn, sq, true);
		sq.clear();
		sq.add("a7");
		sq.add("b7");
		sq.add("c7");
		sq.add("d7");
		sq.add("e7");
		sq.add("f7");
		sq.add("g7");
		sq.add("h7");
		putPiece(Pieces.Pawn, sq, false);
	}

	public void putPiece(Pieces piece, List<String> squareName, boolean isWhite) {
		for (String sn : squareName) {
			putPiece(piece, sn, isWhite);
		}
	}

	public void putPiece(Pieces piece, String squareName, boolean isWhite) {
		Square square;
		square = squares.get(squareName);
		Piece occupier = null;
		switch (piece) {
		case King:
			occupier = new King(this, square, isWhite);
			break;
		case Bishop:
			occupier = new Bishop(this, square, isWhite);
			break;
		case Knight:
			occupier = new Knight(this, square, isWhite);
			break;
		case Pawn:
			occupier = new Pawn(this, square, isWhite);
			break;
		case Queen:
			occupier = new Queen(this, square, isWhite);
			break;
		case Rook:
			occupier = new Rook(this, square, isWhite);
			break;
		default:
			System.out.println("Board:Unknown piece.");
		}

		occupier.setWhite(isWhite);
		square.setOccupier(occupier);
	}

	public void setFEN(String fen) {
		/** TODO implement method * */

	}

	public String getFEN() {
		/** TODO implement method * */
		return "";
	}

	public boolean isCheck() {
		/** TODO implement method * */
		return false;
	}

	public boolean isGameOver() {
		return gameResult != null;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public ColorPlayer getTurn() {
		return turn;
	}

	public List<Square> getValidMoves(Square squareOfBoard) {
		/** TODO implement method * */
		return new ArrayList<Square>();
	}

	public boolean addMove(Square source, Square destination) {
		/*
		 * Tries to move the piece located om source to destination. Returns
		 * True if that was a valid move. The position arguments must be tuples
		 * containing x,y value Ex. (4,6) If this method returns False you can
		 * use the getReason method to determin why.
		 */
		/** TODO implement method * */
		return true;
	}

	public boolean addMove(String move) {
		/*
		 * Adds a move using several different standards of the Algebraic chess
		 * notation. AN Examples: 'e2e4' 'f1d1' 'd7-d8' 'g1-f3' SAN Examples:
		 * 'e4' 'Rfxd1' 'd8=Q' 'Nxf3+' LAN Examples: 'Pe2e4' 'Rf1xd1' 'Pd7d8=Q'
		 * 'Ng1xf3+'
		 * 
		 * note mdch: check style 12 and see if it is one of these. We probably
		 * only need one to start with. It is possible we'll need more if pgn
		 * files also come in diffrent notations
		 */
		/** TODO implement method * */
		return addMove(null, null);
	}

	public Reason getReason() {
		return reason;
	}

	public void setPromotion(Pieces promotion) {
		this.promotionPiece = promotion;
	}

	public Pieces getPromotion() {
		return this.promotionPiece;
	}

	public Pieces getPromotionPiece() {
		return promotionPiece;
	}

	public void setPromotionPiece(Pieces promotionPiece) {
		this.promotionPiece = promotionPiece;
	}

	public Map<String, Square> getSquares() {
		return squares;
	}

	public void setSquares(Map<String, Square> squares) {
		this.squares = squares;
	}

	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public void setTurn(ColorPlayer turn) {
		this.turn = turn;
	}

	public void printBoard() {
		/* prints the board to standard output */
		System.out.println(this.toString());
	}

	/**
	 * Helper methods for Rooks and Queens
	 * 
	 */

	public Set<Square> getSquaresUnder(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		int v = start.getNumber() % 10;
		v++;// add one to start under the square
		for (; v < MAXVERTICAL; v++) {
			Square square = Board.int2Square(this, h * 10 + v);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				return result;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				return result;
			}
		}
		return result;
	}

	public Set<Square> getSquaresAbove(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		int v = start.getNumber() % 10;
		v--;// subtract one to start above the start square
		for (; v > 0; v--) {
			Square square = Board.int2Square(this, h * 10 + v);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				continue;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				return result;
			}
		}
		return result;
	}

	public Set<Square> getSquaresRightOf(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		h++;// add one to start next to the square
		int v = start.getNumber() % 10;
		for (; h < MAXHORIZONTAL; h++) {
			Square square = Board.int2Square(this, h * 10 + v);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				return result;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				return result;
			}
		}
		return result;
	}

	public Set<Square> getSquaresLeftOf(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		int v = start.getNumber() % 10;
		h--;// start left of the start square
		for (; h > 0; h--) {
			Square square = Board.int2Square(this, h * 10 + v);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				continue;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				return result;
			}
		}
		return result;
	}

	public SquareState getSquareState(Square destination, Square source) {
		/*
		 * source can never be empty, its the square with the piece on who is
		 * checking its moves
		 */
		if (destination != null) {
			if (destination.isEmpty()) {
				return SquareState.GOOD;
			} else {
				// if the field is occupied by an opponents piece that is not a
				// king, it can be taken
				// so it is a possible field
				Piece piece = destination.getOccupier();
				if ((piece.isWhite() == source.getOccupier().isWhite())
						|| piece instanceof King) {
					// it's one of ours or its a king!
					return SquareState.BAD;
				} else {
					// its an opponents piece. If it is not his king, its a
					// valid square but we should stop there then
					return SquareState.GOOD_BUT_LAST_ONE;
				}
			}
		}
		// if we get here, we are probably over an edge of the board
		return SquareState.BAD;
	}

	public Dimension getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public Point getLocation() {
		
		return location;
	}

}
