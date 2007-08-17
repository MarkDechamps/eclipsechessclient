package chessclient.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import chessclient.editpolicies.PieceEditPolicy;
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
   
		//installEditPolicy(EditPolicy.COMPONENT_ROLE, new ReparentEditPolicy2());
		
		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new GameEditPolicy());
		installEditPolicy("bla", new PieceEditPolicy());
		
	}

	@Override
	public void activate() {
		super.activate();
	}
	
	
}
