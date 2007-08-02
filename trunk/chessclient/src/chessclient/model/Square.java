package chessclient.model;



/* mdch: This class represents a square on the board
 * 
 * */
public class Square {
	private Boolean isWhite;	
	private String name;
	private Piece occupier = null;
	private int number = 0;
	
	public Square(Boolean isWhite,String name,int number){
		this.name = name;
		setIsWhite(isWhite);
		setNumber(number);
		
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void clear() {
		occupier = null;
		
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isEmpty(){
		return getOccupier() == null;
	}
}
