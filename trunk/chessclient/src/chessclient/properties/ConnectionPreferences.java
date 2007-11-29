package chessclient.properties;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import chessclient.Activator;

public class ConnectionPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	
	public ConnectionPreferences(int style) {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Connection");
	}

	@Override
	protected Control createContents(Composite parent) {

		return null;
	}

	@Override
	protected void createFieldEditors() {
		addField(new StringFieldEditor("Host", ConnectionPreferenceConstants.HOSTID, this.getFieldEditorParent()));
		
	}

	public void init(IWorkbench workbench) {		
	}
}
