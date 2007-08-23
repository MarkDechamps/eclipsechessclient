package chessclient.editparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableLayeredPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;

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

		Figure layer = new ScalableLayeredPane();
		layer.setBounds(new Rectangle(location.x, location.y, size.width,
				size.height));
		layer.setBackgroundColor(ColorConstants.darkGray);
		layer.setOpaque(true);
		layer.setLayoutManager(new FreeformLayout());
		return layer;

	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				System.out.println("BoardEditpart Create:"
						+ request.getNewObject());

				return new Command() {
				};
			}

			@Override
			protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
				return null;
			}

			
		});
		
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
		Board board = (Board) getModel();
		boolean whiteOnTop = board.isWhiteOnTop();
		int multiplier = getSizeForSquare().width;
		int x=0,y=0;
		if(!whiteOnTop){
			x = ((number / 10)) * multiplier;
			y = (Board.MAXVERTICAL - (number % 10) - 1) * multiplier;
		}else{
			x = (Board.MAXHORIZONTAL - (number / 10)-1) * multiplier;
			y = ((number % 10)) * multiplier;
		}
		
		/* add the offset to the location of the board */
		x += board.getLocation().x;
		y += board.getLocation().y;
		Point location = new Point(x, y);
		return location;
	}

//	public static EditPart getDraggedPiece() {
//		return BoardEditPart.draggedPiece;
//	}
//
//	public static void setDraggedPiece(EditPart argDraggedPiece) {
//		BoardEditPart.draggedPiece = argDraggedPiece;
//	}
}
