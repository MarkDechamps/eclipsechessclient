package chessclient.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.ImageRegistry;

import chessclient.Activator;
import chessclient.PieceImagePaths;
import chessclient.editpolicies.PieceEditPolicy;
import chessclient.model.Piece;

public class PieceEditPart extends AbstractGraphicalEditPart implements
		EditPart {

	IFigure figure;
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			Piece piece = (Piece) getModel();
			/*Label label = new Label(piece.toString());
			label.setLocation(piece.getLocation());
			label.setBackgroundColor(ColorConstants.black);
			label.setForegroundColor(ColorConstants.green);
			label.setSize(piece.getSize());
			label.setOpaque(false);			
			figure = label;*/
			ImageRegistry registry;
			registry = Activator.getDefault().getImageRegistry();
			figure = new ImageFigure(registry.get(piece.getImageName()));
			
			figure.setLocation(piece.getLocation());
			figure.setSize(piece.getSize());
			figure.setOpaque(false);
			
		}
		return figure;
	}
	
	@Override
	protected void createEditPolicies() {
   
		//installEditPolicy(EditPolicy.COMPONENT_ROLE, new ReparentEditPolicy2());
		
		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new GameEditPolicy());
		installEditPolicy("bla", new PieceEditPolicy());
		
	}

	@Override
	public void activate() {
		super.activate();
	}
	
	
}
