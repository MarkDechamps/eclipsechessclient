package chessclient.editparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.swt.graphics.Color;

import chessclient.model.Piece;
import chessclient.model.Square;

public class SquareEditPart extends AbstractGraphicalEditPart implements
		EditPart, Observer {

	IFigure figure;

	Color background;

	Color mouseOver;

	boolean moveMade = false;

	@Override
	protected IFigure createFigure() {
		Square square = (Square) getModel();
		RectangleFigure rf = new RectangleFigure();
		rf.setSize(square.getSize());
		rf.setLocation(square.getLocation());
		rf.setOpaque(true);
		background = square.getIsWhite() ? ColorConstants.white
				: ColorConstants.red;
		mouseOver = square.getIsWhite() ? ColorConstants.yellow
				: ColorConstants.yellow;

		rf.setBackgroundColor(background);
		rf.setLayoutManager(new XYLayout());
		figure = rf;
		return rf;
	}

	@Override
	protected void createEditPolicies() {

		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				System.out.println("SquareEditpart Create:"
						+ request.getNewObject() + " " + request);

				return new Command() {
				};
			}

			@Override
			public EditPart getTargetEditPart(Request request) {
				// System.out.println("gettargeteditpart "+request);
				if (request instanceof SelectionRequest) {
					SelectionRequest sel = (SelectionRequest) request;
					if (!sel.isAnyMouseButtonPressed()) {
						if (BoardEditPart.getDraggedPiece() != null) {
							System.out.println("SquareEditpart: piece:"
									+ BoardEditPart.getDraggedPiece()
									+ " is being dropped on:" + this);
							
							//fireChildAdded(BoardEditPart.getDraggedPiece(), 0);
							
							getHost().performRequest(new CreateRequest("a piece is moved"));
							BoardEditPart.setDraggedPiece(null);
						}
					}
				}
				return super.getTargetEditPart(request);
			}
			
			@Override
			protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
				// TODO Auto-generated method stub
				return null;
			}

		});
		
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

	@Override
	public void activate() {
		Square square = (Square) getModel();
		square.addObserver(this);
		getContentPane().addMouseListener(new MouseListener() {

			public void mouseDoubleClicked(MouseEvent me) {
				System.out.println("mousedoubleclicked");

			}

			public void mousePressed(MouseEvent me) {
				System.out.println("mousepressed on " + me.getSource());

			}

			public void mouseReleased(MouseEvent me) {
				System.out.println("mousereleased");
				moveMade = true;

			}

		});

		getContentPane().addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent me) {
				System.out.println("mousedragged");

			}

			public void mouseEntered(MouseEvent me) {
				if (figure != null) {

					figure.setBackgroundColor(mouseOver);
				}

			}

			public void mouseExited(MouseEvent me) {
				if (figure != null) {

					figure.setBackgroundColor(background);
				}

			}

			public void mouseHover(MouseEvent me) {
				System.out.println("mousehover");

			}

			public void mouseMoved(MouseEvent me) {

			}
		});

		super.activate();
	}

	@Override
	public void deactivate() {
		Square square = (Square) getModel();
		square.deleteObserver(this);
		super.deactivate();
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println("SquareEditpart does update");
		refreshVisuals();
	}

	public boolean isMoveMade() {
		return moveMade;
	}

	public void setMoveMade(boolean moveMade) {
		this.moveMade = moveMade;
	}

}
