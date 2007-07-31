package chessclient.model;

import java.util.Set;
import java.util.TreeSet;


/* mdch: This class represents a square on the board
 * 
 * */
public class Square {
	private Boolean isWhite;
	/* the pieces that have an influence on this square */
	private Set<Piece> whiteAttackers = new TreeSet<Piece>();
	private Set<Piece> blackAttackers = new TreeSet<Piece>();
	
	private Piece occupier = null;
	
	public Square(Boolean isWhite){
		setIsWhite(isWhite);
	}
	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	/* Each time a piece moves, it should:
	 * 1)remove itself (as attacker) from all previous fields it was attacking (this can later be optimized if this should be necessary)
	 * 2)add itself as an attacker to all fields it has influence on (NOT the field the piece is on itself! a piece does not attack the
	 * field it stands on!)
	 * 3)inform the field it lands on. 
	 * This field should then inform all pieces (well, not all, only pieces with long influences like Q,R,B) that attack it that 
	 * they should refresh their 'attacking fields'. This is necessary because a piece can block influences of another (e.g. pawn moves
	 * in the line of a Rook)
	 *  */
	
	public boolean addAttacker(Piece attacker){
		assert attacker!=null:"Square: addAttacker is NULL";
		boolean result = true;
		if(attacker.isWhite()){
			result = whiteAttackers.add(attacker);
		}else{
			result = blackAttackers.add(attacker);
		}
		
		return result;
	}
	
	public boolean removeAttacker(Piece attacker){
		assert attacker!=null:"Square: removeAttacker is NULL";
		boolean result = true;
		if(attacker.isWhite()){
			result = whiteAttackers.remove(attacker);
		}else{
			result = blackAttackers.remove(attacker);
		}
		return result;
	}
	public Piece getOccupier() {
		return occupier;
	}
	public void setOccupier(Piece occupier) {
		this.occupier = occupier;
	}
}
