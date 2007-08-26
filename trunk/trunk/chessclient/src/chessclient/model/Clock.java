package chessclient.model;

import java.util.Observable;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class Clock extends Observable {

	private int hours, minutes, seconds, hundreds;

	private Dimension size;

	private Point location;

	private boolean running;

	private boolean isWhite;

	private Font font = new Font(Display.getDefault(),"Arial",20,SWT.NORMAL);
	public Clock(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	protected int getHours() {
		return hours;
	}

	protected void setHours(int hours) {
		this.hours = hours;
	}

	protected int getHundreds() {
		return hundreds;
	}

	protected void setHundreds(int hundreds) {
		this.hundreds = hundreds;
	}

	protected int getMinutes() {
		return minutes;
	}

	protected void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	protected boolean isRunning() {
		return running;
	}

	protected void setRunning(boolean running) {
		this.running = running;
	}

	protected int getSeconds() {
		return seconds;
	}

	protected void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String toString() {
		String result = isWhite?"White: ":"Black: "; 
		result += hours + ":" + minutes + ":" + seconds + ":"
				+ hundreds;
		return result;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void calculateSize() {
		int h = font.getFontData()[0].getHeight()*2;
		int w = font.getFontData()[0].getHeight()* this.toString().length();
		size = new Dimension(w,h);
		setSize(size);
	}
}
