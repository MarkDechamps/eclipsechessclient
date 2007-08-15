package chessclient.editpolicies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import chessclient.editparts.BoardEditPart;
import chessclient.editparts.PieceEditPart;
import chessclient.model.Piece;

public class PieceEditPolicy extends /*XYLayoutEditPolicy*/NonResizableEditPolicy implements
		EditPolicy {

	//IFigure feedbackFigure = null;
	
	@Override
	protected IFigure createDragSourceFeedbackFigure() {
			
			IFigure feedbackFigure = new Label(getHost().getModel().toString());
			feedbackFigure.setSize(((Piece)getHost().getModel()).getSize());
			//feedbackFigure.setBackgroundColor(ColorConstants.black);
			feedbackFigure.setForegroundColor(ColorConstants.blue);
			feedbackFigure.setVisible(true);
			feedbackFigure.setOpaque(true);
			addFeedback(feedbackFigure);
			System.out.println("piedeeditpolicy figure:"+feedbackFigure);
		return feedbackFigure;
	}
	
	
	
	@Override
	public Command getCommand(Request request) {
		if(request.getType() == RequestConstants.REQ_ORPHAN){
			if(request instanceof ChangeBoundsRequest){
				ChangeBoundsRequest cbr = (ChangeBoundsRequest)request;
				//System.out.println(cbr.getEditParts()+" "+cbr.getLocation()+" "+cbr.getMoveDelta());
				PieceEditPart piece = (PieceEditPart)cbr.getEditParts().get(0);
				//System.out.println(piece.getParent());
				BoardEditPart.setDraggedPiece(piece);
			}
			
			//return new PieceMoveCommand((Piece)getHost(),null);
			//return super.getCommand(request);
			return null;
		}
		
		System.out.println("Pieceeditpolicy unhandled request:"+request.getType());
		return super.getCommand(request);
	}
	
	
}
