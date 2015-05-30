package webchat.ui;

public class UserInfo {
	String newUser;
	String newPass;
	String newPass2;

	public void getInfo(String newUser1, String newPass1, String newPass21) {
		newUser = newUser1;
		newPass = newPass1;
		newPass2 = newPass21;
	}

	public boolean userpasscheck() {
		if (newUser.indexOf(" ") == -1 && newPass.equals(newPass2)) {
			return true;
		} else
			return false;
	}
}
