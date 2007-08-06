package chessclient.editparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import chessclient.model.Board;
import chessclient.model.Square;

public class BoardEditPart extends AbstractGraphicalEditPart implements
		EditPart {

	private Dimension size = null;

	private Point location;

	@Override
	protected IFigure createFigure() {
		/*
		 * RectangleFigure value = new RectangleFigure(); value.setOpaque(true);
		 * value.setBackgroundColor(ColorConstants.blue);
		 * 
		 * if (size == null) { System.out.println("BoardEditPart takes default
		 * size (500,500)"); size = new Dimension(500, 500); }
		 * 
		 * value.setSize(size);
		 * 
		 * return value;
		 */

		Board board = (Board) getModel();
		if (size == null) {
			size = board.getSize();
		}
		if (location == null) {
			location = board.getLocation();
		}

		Figure layer = new FreeformLayer() {
			@Override
			public Rectangle getFreeformExtent() {
				return new Rectangle(0,0,1024,768);
			}

			public void setFreeformBounds(Rectangle bounds) {
				bounds.x = 0;
				bounds.y = 0;
				super.setFreeformBounds(bounds);
			}
		};
		layer.setBounds(new Rectangle(location.x, location.y, size.width,
				size.height));
		layer.setBackgroundColor(ColorConstants.gray);
		layer.setOpaque(true);
		layer.setLayoutManager(new XYLayout());
		return layer;

	}

	@Override
	protected void createEditPolicies() {
		//installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		//installEditPolicy("pieces nonresizableeditpolicy",
			//	new NonResizableEditPolicy());
		//XYLayout layout = (XYLayout) getContentPane().getLayoutManager();
		//installEditPolicy("xylayoutpolixy", new PieceXYLayoutEditPolicy());
		
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Dimension getSize() {
		return this.size;
	}

	@Override
	protected List getModelChildren() {
		Board b = (Board) getModel();
		Map<String, Square> squares = b.getSquares();
		Collection<Square> squaresCollection = squares.values();
		List<Square> result = new ArrayList<Square>();

		for (Square s : squaresCollection) {
			result.add(s);
			s.setLocation(getLocationFor(s.getNumber()));
			s.setSize(getSizeForSquare());
		}

		return result;
	}

	private Dimension getSizeForSquare() {
		int w = getSize().width;
		int h = getSize().height;
		int min = w < h ? w : h;
		int s = min / Board.MAXHORIZONTAL;
		return new Dimension(s, s);

	}

	private Point getLocationFor(int number) {

		int multiplier = getSizeForSquare().width;
		int x = (Board.MAXHORIZONTAL - (number / 10) - 1)
		* multiplier;
		int y = (Board.MAXVERTICAL - (number % 10) - 1)
				* multiplier;
		Board board = (Board)getModel();
		x+= board.getLocation().x;
		y+= board.getLocation().y;
		Point location = new Point( x, y);
		return location;
	}
}
