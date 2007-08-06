package chessclient.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.ui.IPropertyListener;

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
		viewer.setEditPartFactory(new BoardEditPartFactory());
		/* root edit part that scales */
		// viewer.setRootEditPart(new ScalableFreeformRootEditPart());
		FreeformGraphicalRootEditPart fgr = new FreeformGraphicalRootEditPart();
		viewer.setRootEditPart(fgr);
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
		viewer.setContents(new Board());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	
}
