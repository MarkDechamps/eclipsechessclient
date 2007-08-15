package chessclient.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for chessclient.model.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(KingTest.class);
		//$JUnit-END$
		return suite;
	}

}
