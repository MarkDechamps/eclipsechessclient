package chessclient.editpolicies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import chessclient.editparts.PieceEditPart;
import chessclient.model.Piece;

public class PieceXYLayoutEditPolicy extends XYLayoutEditPolicy implements
		EditPolicy {


	@Override
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		System.out.println("PieceXYLayoutEditPolicy 2 "+child+" "+constraint);
		return super.createChangeConstraintCommand(request, child, constraint);
	}
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {

		System.out.println("PieceXYLAyout createchangeconstraintcommand 2 "+child+" "+constraint);
		if (child instanceof PieceEditPart
				&& constraint instanceof Rectangle) {
			System.out.println("PieceXYLayoutPolicy has constraints of:"
					+ constraint);
			// return a command that can move and/or resize a Shape
			return new PieceMoveCommand((Piece) child.getModel(),
					(Rectangle) constraint);
		}
		return null;
	}
	
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		System.out.println("PieceXYLayoutEditPolicy getCreateCommand "+request);
		return null;
	}
	
	@Override
	public Command getCommand(Request request) {
		ChangeBoundsRequest cbr = (ChangeBoundsRequest)request;
		System.out.println("PieceXYLayoutEditPolicy getCommand "+cbr.getEditParts());
		return super.getCommand(request);
	}
	
}
