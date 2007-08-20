package chessclient.model;

import java.util.Observable;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/* mdch: This class represents a square on the board
 * 
 * */
public class Square extends Observable implements Comparable{
	private Boolean isWhite;

	private String name;

	private Piece occupier = null;

	private int number = 0;

	private Point location;

	private Dimension size;

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point getLocation() {
		return location;
	}

	public Square(Boolean isWhite, String name, int number) {
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
		if (occupier != null) {
			occupier.setLocation(getLocation());
			occupier.setSize(getSize());
		}
		setChanged();
		notifyObservers(occupier);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void clear() {
		occupier = null;
		setChanged();
		notifyObservers();

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isEmpty() {
		return getOccupier() == null;
	}

	public void setLocation(Point locationFor) {
		this.location = locationFor;

	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Square other = (Square) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)){
			return false;
		}
			
		return true;
	}

	public int compareTo(Object arg0) {
		if(arg0 instanceof Square){
			Square other = (Square)arg0;
			if(other.getNumber()> getNumber())return 1;
			else if(other.getNumber()== getNumber())return 0;
		}
		
		return -1;
	}
}
