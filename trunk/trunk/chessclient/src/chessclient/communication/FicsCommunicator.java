package chessclient.communication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import chessclient.user.UserInfo;

public class FicsCommunicator implements Communication {

	private String host ="freechess.org";
	private UserInfo userInfo;
	private Socket client;
	
	
	public boolean connect() throws IOException {
		client = new Socket();
		client.connect(new InetSocketAddress(host,23));		
		return false;
	}

	public boolean isConnected() {
		
		return false;
	}

	public void setHost(String host) {
		this.host = host; 
	}

	public void setUserInfo(UserInfo info) {
		this.userInfo = info;
	}

}
