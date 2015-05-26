package webchat.ui;

public class UserLogin {
	String user, pw, serverURL;
	
	public UserLogin(String user1, String pw1, String serverURL1) {
		user = user1;
		pw = pw1;
		serverURL = serverURL1;
	}
	
	public boolean infoCheck() {
		if (user.equals("") || pw.equals("") || serverURL.equals(""))
			// check against database info
			return false;
		else
			return true;
	}
}
