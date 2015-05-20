package users;

import java.io.Serializable;

public class User implements Serializable {
	
	private String username;
	private byte[] passwordHash;
	private String instanceHash;
	
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

	public String getInstanceHash() {
		return instanceHash;
	}

	public void setInstanceHash(String instanceHash) {
		this.instanceHash = instanceHash;
	}
	
}
