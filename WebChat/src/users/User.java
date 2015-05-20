package users;

import java.io.Serializable;

public class User implements Serializable {
	
	private String username;
	private byte[] passwordHash;
	
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
	
}
