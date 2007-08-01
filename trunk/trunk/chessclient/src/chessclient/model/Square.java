package chessclient.model;

import java.util.Set;
import java.util.TreeSet;


/* mdch: This class represents a square on the board
 * 
 * */
public class Square {
	private Boolean isWhite;	
	
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
	
	
	
	public Piece getOccupier() {
		return occupier;
	}
	public void setOccupier(Piece occupier) {
		this.occupier = occupier;
	}
}
