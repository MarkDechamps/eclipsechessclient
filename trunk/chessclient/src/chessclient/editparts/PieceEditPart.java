package chessclient.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import chessclient.model.Piece;

public class PieceEditPart extends AbstractGraphicalEditPart implements
		EditPart {

	IFigure figure;

	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			Piece piece = (Piece) getModel();
			Label label = new Label(piece.toString());
			label.setLocation(piece.getLocation());
			label.setBackgroundColor(ColorConstants.black);
			label.setForegroundColor(ColorConstants.green);
			label.setSize(piece.getSize());
			label.setOpaque(false);
			figure = label;
		}
		return figure;
	}
	
	@Override
	protected void createEditPolicies() {
		
	}

}
