package chessclient;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements PieceImagePaths {

	// The plug-in ID
	public static final String PLUGIN_ID = "chessclient";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
//	 in this code we will load icons from a directory called 'icons' in our plugin

	public final String ICON_PATH = "icons/";
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {

		URL baseURL = getBundle().getEntry("/"); //$NON-NLS-1$

		// NAME_OF_ICON_FILE is the name of the icon (i.e. foo.jpg)
	        // This name will also be used as the key to extract the image
	        // from the registry in the future.  A list of static final strings
	        // with these names can be kept in an interface for easy reference.
		createImageDescriptor(reg, WROOK, baseURL);
		createImageDescriptor(reg, WKNIGHT, baseURL);
		createImageDescriptor(reg, WBISHOP, baseURL);
		createImageDescriptor(reg, WQUEEN, baseURL);
		createImageDescriptor(reg, WKING, baseURL);
		createImageDescriptor(reg, WPAWN, baseURL);
		createImageDescriptor(reg, BROOK, baseURL);
		createImageDescriptor(reg, BKING, baseURL);
		createImageDescriptor(reg, BKNIGHT, baseURL);
		createImageDescriptor(reg, BQUEEN, baseURL);
		createImageDescriptor(reg, BBISHOP, baseURL);
		createImageDescriptor(reg, BPAWN, baseURL);
		
		

	}

	private void createImageDescriptor(ImageRegistry reg, String id, URL baseURL) {
		URL url = null;
		try {
			url = new URL(baseURL, ICON_PATH + id);
		} catch (MalformedURLException e) {
	             e.printStackTrace();
		}
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		reg.put(id, desc);
	}
}
