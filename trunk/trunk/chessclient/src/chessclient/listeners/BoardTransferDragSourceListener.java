package chessclient.listeners;

import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;

public class BoardTransferDragSourceListener implements
		TransferDragSourceListener {

	public Transfer getTransfer() {
		System.out.println("getTransfer");
		return new Transfer(){

			@Override
			public TransferData[] getSupportedTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected int[] getTypeIds() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected String[] getTypeNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isSupportedType(TransferData transferData) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected void javaToNative(Object object, TransferData transferData) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected Object nativeToJava(TransferData transferData) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	public void dragFinished(DragSourceEvent event) {
		System.out.println("Drag finished!"+event.getSource());

	}

	public void dragSetData(DragSourceEvent event) {
		System.out.println("Drag setdata!"+event.getSource());

	}

	public void dragStart(DragSourceEvent event) {
		System.out.println("Drag start!"+event.getSource());

	}

}
