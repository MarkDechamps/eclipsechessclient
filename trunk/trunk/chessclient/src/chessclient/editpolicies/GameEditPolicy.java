package chessclient.editpolicies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;

import chessclient.editparts.BoardEditPart;
import chessclient.editparts.SquareEditPart;
import chessclient.model.Board;
import chessclient.model.Piece;
import chessclient.model.Square;

public class GameEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		System.out.println("SquareEditpart Create:" + request.getNewObject()
				+ " " + request);

		return new Command() {
		};
	}
	
	@Override
	public EditPart getTargetEditPart(Request request) {
		System.out.println("gettargeteditpart "+request);
		if (request instanceof SelectionRequest) {
			SelectionRequest sel = (SelectionRequest) request;
			System.out.println("wtf?"+sel.isAnyMouseButtonPressed()+" "+BoardEditPart.getDraggedPiece()+" "+sel.getType());
			if (!sel.isAnyMouseButtonPressed()) {
				if (BoardEditPart.getDraggedPiece() != null) {
					System.out.println("GameEditpolicy: piece:"
							+ BoardEditPart.getDraggedPiece()
							+ " is being dropped on:" + this);
					try {

						EditPart top = (EditPart)getHost().getRoot().getChildren().get(0);

						Board board = (Board) top.getModel();

						Piece piece = (Piece) BoardEditPart.getDraggedPiece()
								.getModel();

						/* climb up to get the square
						 * if this is a square, great. If it is a piece then we have to climb up
						 * */
						EditPart square = this.getHost();
						while(!(square instanceof SquareEditPart)){
							square = square.getParent();
						}
						
						board.addMove(piece.getSquare(), (Square) square.getModel());

						BoardEditPart.setDraggedPiece(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return super.getTargetEditPart(request);
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		// TODO Auto-generated method stub
		return null;
	}

}
