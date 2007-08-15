package chessclient.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

public class TestLayoutEditPolicy extends NonResizableEditPolicy /*XYLayoutEditPolicy*/ {
	
	/* (non-Javadoc)
	 * @see ConstrainedLayoutEditPolicy#createChangeConstraintCommand(ChangeBoundsRequest, EditPart, Object)
	 */
	private String who;//some id for the caller, for debug reasons	
	public TestLayoutEditPolicy(Boolean dragAllowed,String who) {
		this.who = who;
		setDragAllowed(dragAllowed);
		
	}
	
	@Override
	public boolean understandsRequest(Request req) {
		System.out.println(who+" TestLayoutEditPolicy understands:"+req.getType()+" "+super.understandsRequest(req));
		return super.understandsRequest(req);
	}
	
	@Override
	public Command getCommand(Request request) {
		System.out.println(who+" TestLayoutEditPolicy getcommand for request:"+request.getType());		
		return new Command("TestLayoutEditPolicy command"){};
	}

}