package chessclient.views;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.internal.PartSite;

public class ConsoleInput implements IStructuredContentProvider {

	//for testing purposes, we just have a list as input
	List<String> input;
	
	protected List<String> getInput() {
		return input;
	}

	protected void setInput(List<String> input) {
		this.input = input;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		System.out.println("ConsoleInput input changed");
		if(newInput instanceof List){
			input = (List<String>)newInput;
		}
		

	}

	public Object[] getElements(Object inputElement) {
		
		
		
		/* for each element returned of the previous element, the children are asked
		 * */
		if(inputElement instanceof PartSite){
			System.out.println("ConsoleInput getElements List");
			return input.toArray();
		}
		return new Object[]{};
	}

}
