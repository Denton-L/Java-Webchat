package webchat.ui;

/**
 * Deals with registration.
 *
 * @author Jing Yi Xie
 * @version 2015-05-25
 */
public class UserInfo {
	/**
	 * The string that contains the new user.
	 */
	private String newUser;
	/**
	 * The string that contains the new password.
	 */
	private String newPass;
	/**
	 * The string that contains the new confirmed password.
	 */
	private String newPass2;
	
	/**
	 * 
	 * @param newUser1 The string that contains the new username.
	 * @param newPass1 The string that contains the new password.
	 * @param newPass21 The string that contains the confirmed password.
	 */
	public void getInfo(String newUser1, String newPass1, String newPass21) {
		this.newUser = newUser1;
		this.newPass = newPass1;
		this.newPass2 = newPass21;
	}
	
	/**
	 * 
	 * @return boolean Checks if information is expected.
	 */
	public boolean userpasscheck() {
		if (this.newUser.indexOf(" ") == -1
				&& this.newPass.equals(this.newPass2)) {
			return true;
		} else {
			return false;
		}
	}
}
