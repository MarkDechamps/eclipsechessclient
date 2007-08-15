package chessclient.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import chessclient.editpolicies.GameEditPolicy;
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
   
//		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy(){
//			@Override
//			public Command getCommand(Request request) {
//				//System.out.println("PieceEditpart:"+request.getType());
////				if(request instanceof ChangeBoundsRequest){
////					ChangeBoundsRequest cbr = (ChangeBoundsRequest)request;
////					System.out.println("PieceEditpart Changebounds:"+cbr.getEditParts());
////				}
////				System.out.println("pieceeditpart "+request.getType()+" "+request);
////				if(request.getType() == RequestConstants.REQ_ORPHAN){
////					BoardEditPart.setDraggedPiece(getHost());
////				}
//				
//				return new Command(){};
//			}
//		});
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new GameEditPolicy());
		installEditPolicy("bla", new PieceEditPolicy());
		
	}

	@Override
	public void activate() {
		super.activate();
	}
	
	
}
