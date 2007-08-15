package chessclient.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.requests.TargetRequest;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.util.TransferDropTargetListener;

import chessclient.factory.BoardEditPartFactory;
import chessclient.model.Board;

public class BoardEditor extends GraphicalEditor {

	public BoardEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
//		TransferDropTargetListener dropSourceListener = new BoardTransferDropTargetListener();
		//viewer.addDropTargetListener(dropSourceListener);
		viewer.setEditPartFactory(new BoardEditPartFactory());
		/* root edit part that scales */
		viewer.setRootEditPart(new ScalableRootEditPart());
		//FreeformGraphicalRootEditPart fgr = new FreeformGraphicalRootEditPart();
		//viewer.setRootEditPart(fgr);
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
		viewer.setContents(new Board());
		
		TransferDropTargetListener dropTargetListener = new AbstractTransferDropTargetListener(viewer){

			@Override
			protected void updateTargetRequest() {
				System.out.println("BoardEditor mouselistener: updatetargetrequest");
				
			}
			@Override
			protected Request createTargetRequest() {
				System.out.println("BoardEditor mouselistener: createrequest");
				return new Request(){};
			}

			
		};
		viewer.addDropTargetListener(dropTargetListener);
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	
}
