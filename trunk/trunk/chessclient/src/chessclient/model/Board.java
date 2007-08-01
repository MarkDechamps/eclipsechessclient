package chessclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import chessclient.enums.ColorPlayer;
import chessclient.enums.GameResult;
import chessclient.enums.Pieces;
import chessclient.enums.Reason;

/* this is the root of the model
 * note: ! if the model changes, you MUST call 
 * setChanged() and notifyAll() otherwise the drawing will not be
 * updated
 * 
 *
 * */
public class Board extends Observable{
	Map<String, Square> squares;

	private Pieces promotionPiece;

	private Reason reason;

	private ColorPlayer turn;

	private GameResult gameResult;

	public void resetBoard() {
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
		 * only need one to start with. 
		 * It is possible we'll need more if pgn files also come in diffrent 
		 * notations
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

	
}
