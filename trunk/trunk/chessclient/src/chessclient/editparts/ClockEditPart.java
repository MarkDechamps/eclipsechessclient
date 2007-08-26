package chessclient.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import chessclient.model.Clock;

public class ClockEditPart extends AbstractGraphicalEditPart implements EditPart{

	@Override
	protected IFigure createFigure() {
		Clock clock = (Clock)getModel();
		IFigure text = new Label(clock.toString());
		text.setLocation(clock.getLocation());
		text.setSize(clock.getSize());
		text.setFont(clock.getFont());
		return text;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

}
