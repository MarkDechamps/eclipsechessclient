package chessclient.model;


/* mdch: This class represents a square on the board
 * 
 * */
public class Square {
	private Boolean isWhite;

	public Square(Boolean isWhite){
		setIsWhite(isWhite);
	}
	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}	
}
