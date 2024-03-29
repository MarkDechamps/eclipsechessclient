package chessclient.editparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.swt.graphics.Color;

import chessclient.editpolicies.ReparentXYLayoutEditPolicy;
import chessclient.model.Piece;
import chessclient.model.Square;

public class SquareEditPart extends AbstractGraphicalEditPart implements
		EditPart, Observer{

	IFigure figure;

	Color background;

	Color mouseOver;

	//boolean moveMade = false;

	@Override
	protected IFigure createFigure() {
		
		RectangleFigure rf = new RectangleFigure();
		initSquare(rf);
		rf.setLayoutManager(new XYLayout());
		figure = rf;
		return rf;
	}

	private void initSquare(IFigure rf){
		Square square = (Square) getModel();
		square.addObserver(this);
		rf.setSize(square.getSize());
		rf.setLocation(square.getLocation());
		rf.setOpaque(true);
		background = square.getIsWhite() ? ColorConstants.white
				: ColorConstants.gray;
		mouseOver = square.getIsWhite() ? ColorConstants.yellow
				: ColorConstants.yellow;

		rf.setBackgroundColor(background);
	}
	
	@Override
	protected void createEditPolicies() {

		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new GameEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ReparentXYLayoutEditPolicy());
		installEditPolicy("nohandlesplease", new NonResizableEditPolicy(){
			@Override
			protected List createSelectionHandles() {
				return null;
			}
			@Override
			protected IFigure createDragSourceFeedbackFigure() {
				return null;
			}
			@Override
			public void activate() {
				setDragAllowed(false);
				super.activate();
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
		/*getContentPane().addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent me) {
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
				//System.out.println("mousehover");
			}

			public void mouseMoved(MouseEvent me) {

			}
		});*/

		super.activate();
	}

	@Override
	public void deactivate() {
		Square square = (Square) getModel();
		square.deleteObserver(this);
		super.deactivate();
	}

	public void update(Observable arg0, Object arg1) {
		Square s = (Square)getModel();
		System.out.println("SquareEditpart does update "+getModel()+s.getSize());
		//getParent().refresh();
		
		refresh();
		
	}

//	@Override
//	public void refresh() {
//		Square square = (Square)getModel();
//		System.out.println("update squareeditpart"+square+" "+square.getSize());
//		initSquare(getFigure());
//		super.refresh();
//	}

}
