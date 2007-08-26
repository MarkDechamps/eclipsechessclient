package chessclient.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import chessclient.editparts.BoardEditPart;
import chessclient.editparts.ClockEditPart;
import chessclient.editparts.PieceEditPart;
import chessclient.editparts.SquareEditPart;
import chessclient.model.Board;
import chessclient.model.Clock;
import chessclient.model.Piece;
import chessclient.model.Square;

public class BoardEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		//System.out.println("BoardEditPartFactory an editpart is requested for :\n"+model.toString());
		
		/* this function gets called to obtain editparts for each object in the model
		 * each editpart will be asked for its children and for each child we come here
		 * again to do the same. An editpart is the link between the model en the view.
		 * */
		if(model instanceof Board){
			EditPart ep =  new BoardEditPart();
			ep.setModel(model);
			return ep;
		}else
		if(model instanceof Square){
			EditPart ep = new SquareEditPart();
			ep.setModel(model);
			return ep;
		}else
		if(model instanceof Piece){
			EditPart ep = new PieceEditPart();
			ep.setModel(model);
			return ep;
		}
		if(model instanceof Clock){
			EditPart ep = new ClockEditPart();
			ep.setModel(model);
			return ep;
		}
		else{
			System.out.println("BoardEditPartFactory has no editpart for:"+model);
		}
		
		
		return null;
	}

}
