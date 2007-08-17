package chessclient.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import chessclient.commands.PieceMoveCommand;
import chessclient.editparts.PieceEditPart;
import chessclient.model.Piece;
import chessclient.model.Square;

public class ReparentXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/* note about GEF: 
	 * The editpolicy will get requests. It should return a command every time a request comes in.
	 * If for instance a drag happens to a certain square, every square you pass dragging the piece, will receive
	 * an add request. You go ahead and create that command. It will not be executed. It is only when the drop
	 * happens that the LAST command for that request gets executed. 
	 * 
	 * */
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getCommand(Request request) {
		//System.out.println(this+" getcommand "+request+" "+request.getType());
		return super.getCommand(request);
	}
	@Override
	protected Command getAddCommand(Request generic) {
		//System.out.println(this+" addcommand "+getHost().getModel()+" "+generic+" "+generic.getType()+" "+generic.getExtendedData());
		if(generic.getType() == REQ_ADD){
			ChangeBoundsRequest cbr = (ChangeBoundsRequest) generic;
			//System.out.println("addcommand children "+cbr.getEditParts());
			Piece piece = (Piece) ((PieceEditPart)cbr.getEditParts().get(0)).getModel();
			Square square = (Square)getHost().getModel();
			return new PieceMoveCommand(piece,square);
		}
		
		return null;
	}
}
