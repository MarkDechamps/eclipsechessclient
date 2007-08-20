package chessclient.editpolicies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.jface.resource.ImageRegistry;

import chessclient.Activator;
import chessclient.model.Piece;

public class PieceEditPolicy extends
/* XYLayoutEditPolicy */NonResizableEditPolicy implements EditPolicy {


	
	@Override
	protected List createSelectionHandles() {
		List list = new ArrayList();
		NonResizableHandleKit.addMoveHandle((GraphicalEditPart)getHost(), list);
		return list;
	}
	
	@Override
	protected IFigure createDragSourceFeedbackFigure() {
		ImageRegistry registry = Activator.getDefault().getImageRegistry();
		Piece piece = (Piece)getHost().getModel();
		IFigure feedbackFigure = new ImageFigure(registry.get(piece.getImageName()));
		feedbackFigure.setSize(((Piece) getHost().getModel()).getSize());
		feedbackFigure.setVisible(true);
		feedbackFigure.setOpaque(false);
		addFeedback(feedbackFigure);
		return feedbackFigure;
		
	}
}
