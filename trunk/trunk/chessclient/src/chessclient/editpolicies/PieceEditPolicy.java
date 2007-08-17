package chessclient.editpolicies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

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
}
