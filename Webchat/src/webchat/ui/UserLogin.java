package webchat.ui;

public class UserLogin {
	String user, pw, serverIp;

	public UserLogin(String user1, String pw1) {
		user = user1;
		pw = pw1;
	}

	public boolean infoCheck() {
		if (user.equals("") || pw.equals(""))
			// check against database info
			return false;
		else
			return true;
	}
}
