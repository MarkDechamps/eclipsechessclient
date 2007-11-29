package chessclient.communication;

import java.io.IOException;

import chessclient.user.UserInfo;

public interface Communication {	
	
	void setHost(String host);
	boolean connect() throws IOException;
	void setUserInfo(UserInfo info);
	boolean isConnected();
	
}
