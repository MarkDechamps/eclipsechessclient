package chessclient.communication;

public interface Communication {	
	
	void setHost(String host);
	boolean connect();
	void setUserInfo();
	
}
