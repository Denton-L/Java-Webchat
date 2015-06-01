package webchat.ui;

public class UserInfo {
	String newUser;
	String newPass;
	String newPass2;

	public void getInfo(String newUser1, String newPass1, String newPass21) {
		this.newUser = newUser1;
		this.newPass = newPass1;
		this.newPass2 = newPass21;
	}

	public boolean userpasscheck() {
		if (this.newUser.indexOf(" ") == -1
				&& this.newPass.equals(this.newPass2)) {
			return true;
		} else {
			return false;
		}
	}
}
