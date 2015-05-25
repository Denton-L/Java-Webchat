package ui;

public class UserLogin {
	String user, pw, serverIp;

	public UserLogin(String user1, String pw1, String serverIp1) {
		user = user1;
		pw = pw1;
		serverIp = serverIp1;
	}

	public boolean infoCheck() {
		if (user.equals("") || pw.equals("") || serverIp.equals(""))
			// check against database info
			return false;
		else
			return true;
	}
}
