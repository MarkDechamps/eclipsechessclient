package chessclient.listeners;

import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;

public class BoardTransferDropTargetListener implements
		TransferDropTargetListener {

	public Transfer getTransfer() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEnabled(DropTargetEvent event) {
		System.out.println("BoardDropTargetListener isenabled"+event.getSource());
		return true;
	}

	public void dragEnter(DropTargetEvent event) {
		System.out.println("BoardDropTargetListener dragEnter "+event.widget);

	}

	public void dragLeave(DropTargetEvent event) {
		System.out.println("BoardDropTargetListener dragLeave "+event.widget);

	}

	public void dragOperationChanged(DropTargetEvent event) {
		System.out.println("BoardDropTargetListener dragOperationChanged "+event.widget);

	}

	public void dragOver(DropTargetEvent event) {
		System.out.println("BoardDropTargetListener dragOver "+event.widget);

	}

	public void drop(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

}
