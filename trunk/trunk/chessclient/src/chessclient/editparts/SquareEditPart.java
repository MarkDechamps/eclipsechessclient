package chessclient.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;

import chessclient.editpolicies.PieceXYLayoutEditPolicy;
import chessclient.model.Piece;
import chessclient.model.Square;

public class SquareEditPart extends AbstractGraphicalEditPart implements
		EditPart {

	@Override
	protected IFigure createFigure() {
		Square square = (Square) getModel();
		RectangleFigure rf = new RectangleFigure();
		rf.setSize(square.getSize());
		rf.setLocation(square.getLocation());
		rf.setOpaque(true);
		Color color = square.getIsWhite() ? ColorConstants.white
				: ColorConstants.red;
		rf.setBackgroundColor(color);

		return rf;
	}

	@Override
	protected void createEditPolicies() {
		//installEditPolicy("",new NonResizableEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PieceXYLayoutEditPolicy());
	}

	@Override
	protected List getModelChildren() {
		Square square = (Square) getModel();
		Piece piece = square.getOccupier();
		List<Piece> result = new ArrayList<Piece>();
		if (piece != null) {
			piece.setLocation(square.getLocation());
			piece.setSize(square.getSize());
			result.add(piece);
		}

		return result;
	}
}
