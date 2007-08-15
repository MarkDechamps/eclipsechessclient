package chessclient.editpolicies;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import chessclient.editparts.BoardEditPart;
import chessclient.editparts.PieceEditPart;
import chessclient.model.Piece;

public class PieceEditPolicy extends
/* XYLayoutEditPolicy */NonResizableEditPolicy implements EditPolicy {

	// IFigure feedbackFigure = null;

	@Override
	protected IFigure createDragSourceFeedbackFigure() {

		IFigure feedbackFigure = new Label(getHost().getModel().toString());
		feedbackFigure.setSize(((Piece) getHost().getModel()).getSize());
		// feedbackFigure.setBackgroundColor(ColorConstants.black);
		feedbackFigure.setForegroundColor(ColorConstants.blue);
		feedbackFigure.setVisible(true);
		feedbackFigure.setOpaque(true);
		addFeedback(feedbackFigure);
		return feedbackFigure;
	}

	@Override
	public Command getCommand(Request request) {
		System.out.println("pieceeditpolicy " + request.getType());
		if (request.getType() == RequestConstants.REQ_DELETE
				|| request.getType() == RequestConstants.REQ_MOVE
				|| request.getType() == RequestConstants.REQ_ORPHAN) {
			if (request instanceof GroupRequest) {
				GroupRequest gr = (GroupRequest) request;
				PieceEditPart piece = (PieceEditPart) gr.getEditParts().get(0);
				BoardEditPart.setDraggedPiece(piece);

			}
			// return new PieceMoveCommand((Piece)getHost(),null);
			// return super.getCommand(request);
			return null;
		}

		// System.out.println("Pieceeditpolicy unhandled
		// request:"+request.getType());
		return super.getCommand(request);
	}

	
}
