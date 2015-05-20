package users;

import java.io.Serializable;

public class User implements Serializable {
	
	private String username;
	private byte[] passwordHash;
	private byte[] userInstance;
	
	public User(String username, byte[] passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public byte[] getuserInstance() {
		return userInstance;
	}

	public void setuserInstance(byte[] userInstance) {
		this.userInstance = userInstance;
	}
	
}
