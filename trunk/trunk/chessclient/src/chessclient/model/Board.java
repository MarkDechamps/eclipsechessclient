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
 * note: ! if you implement a method that changes the model , you MUST call 
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

	private boolean whiteOnTop = false;

	public Board() {

		location = new Point(0, 0);
		size = new Dimension(500, 500);
		createBoard(location, size);
		
		/* put the pieces on */
		resetBoard();
	}

	private void createBoard(Point location, Dimension size){
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
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int v = MAXVERTICAL - 1; v > -1; v--) {
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
		// for the color who's move it is,
		// for the king, check the 'knight squares' around them for enemy
		// knights
		// check the diagonals for enemy queens and bishops
		// check the hor and ver squares for enemy rooks and queens
		// check for pawn attacks
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



	public boolean addMove(Square source, Square destination) {
		/*
		 * Tries to move the piece located om source to destination. Returns
		 * True if that was a valid move. The position arguments must be tuples
		 * containing x,y value Ex. (4,6) If this method returns False you can
		 * use the getReason method to determin why.
		 */
		/** TODO implement method * */
		/*
		 * mdch :This method should be implemented for when a move comes in from
		 * our opponent. We ourself will not use this method as our
		 * PieceMoveCommand's do the same and is better design. We should also
		 * make PieceMoveCommands in here. Ask mdch for more info or look at
		 * PieceMoveCommand.
		 */

		// if(source == destination){
		// return true;
		// }
		// Piece piece = source.getOccupier();
		// destination.setOccupier(piece);
		// if(piece!=null){
		// piece.setSquare(destination);
		// }
		// source.setOccupier(null);
		// setChanged();
		// notifyObservers();
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

	public Set<Square> getDiagonals(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		int v = start.getNumber() % 10;

		int vc = v; // the vertical counter
		int hc = h;
		vc++;
		hc++;
		/* Right Down */
		RIGHTDOWN: for (; vc < MAXVERTICAL && hc < MAXHORIZONTAL; vc++, hc++) {
			Square square = Board.int2Square(this, hc * 10 + vc);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				break RIGHTDOWN;// jump out of this loop
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				break RIGHTDOWN;// jump out of this loop
			}
		}

		vc = v;
		hc = h;
		vc++;
		hc--;
		/* Left Down */
		LEFTDOWN: for (; vc < MAXVERTICAL && hc > -1; vc++, hc--) {
			Square square = Board.int2Square(this, hc * 10 + vc);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				break LEFTDOWN;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				break LEFTDOWN;
			}
		}

		vc = v;
		hc = h;
		vc--;
		hc--;
		/* Left Up */
		LEFTUP: for (; vc > -1 && hc > -1; vc--, hc--) {
			Square square = Board.int2Square(this, hc * 10 + vc);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				break LEFTUP;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				break LEFTUP;
			}
		}
		
		vc = v;
		hc = h;
		vc--;
		hc++;
		RIGHTUP: 
		for (; vc > -1 && hc< MAXHORIZONTAL; vc--, hc++) {
			Square square = Board.int2Square(this, hc * 10 + vc);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				break RIGHTUP;
			case GOOD:
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE:
				result.add(square);
				break RIGHTUP;
			}
		}
		return result;
	}

	/**
	 * Helper methods for Rooks and Queens
	 * 
	 */

	public Set<Square> getSquaresUnder(Square start) {
		Set<Square> result = new TreeSet<Square>();
		int h = start.getNumber() / 10;// get the horizontal number
		int v = start.getNumber() % 10;
		v--;// add one to start under the square
		for (; v >-1; v--) {
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
		v++;// add one to start above the start square
		CHECKABOVE: for (; v < MAXVERTICAL; v++) {
			Square square = Board.int2Square(this, h * 10 + v);
			// System.out.println("board getsquaresabove "+v);
			SquareState state = getSquareState(square, start);
			switch (state) {
			case BAD:
				break CHECKABOVE; // break out of the loop!
			case GOOD:// a good valid square
				System.out.println("good " + square);
				result.add(square);
				break;
			case GOOD_BUT_LAST_ONE: // this means there is a piece on that
									// square
				// System.out.println("good but last "+square);
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
		for (; h > -1; h--) {
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

	public SquareState getSquareState(Square destination, Square source) {
		/*
		 * source can never be empty, its the square with the piece on who is
		 * checking its moves
		 */
		//System.out.println("Board checkin "+destination);
		if (destination != null) {
			if (destination.isEmpty()) {
				return SquareState.GOOD;
			} else {
				// if the field is occupied by an opponents piece that is not a
				// king, it can be taken
				// so it is a possible field
				Piece piece = destination.getOccupier();

				if (piece != null
						&& (piece.isWhite() == source.getOccupier().isWhite())
						|| piece instanceof King) {
					// it's one of ours or its a king and we don't take
					// kings..no we don't!
					return SquareState.BAD;
				} else {
					// its an opponents piece. If it is not his king, its a
					// valid square but we should stop there then
					System.out.println("good but last one!"+destination);
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

	public boolean isWhiteOnTop() {
		return whiteOnTop;
	}

	public void setWhiteOnTop(boolean whiteOnTop) {
		this.whiteOnTop = whiteOnTop;
		setChanged();
		notifyObservers();
	}

	public static Square int2Square(Board board, int fieldNumber) {
		int v = fieldNumber % 10;
		int h = fieldNumber / 10;

		/* we add 1 to v because chess boards don't start counting at 0 */
		Square result = board.getSquares().get(
				"" + ((char) ('a' + h)) + (v + 1));
		// System.out.println("int2square "+result);
		return result;
	}

	public void setSize(org.eclipse.swt.graphics.Point newSize) {
		int w = newSize.x;
		int h = newSize.y;
		int side = w<h?w:h;
		this.size = new Dimension(side,side);
		createBoard(location, this.size);
		setChanged();
		notifyObservers();
	}

}
