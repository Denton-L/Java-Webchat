package webchat.users;

import java.io.Serializable;

/**
 * A class that holds the data of a user and will be stored in a database. Note
 * that the {@code userInstance} is a random {@code byte[]} that will uniquely
 * identify this user to the server.
 *
 * @author Denton Liu
 * @version 2015-05-25
 */
public class User implements Serializable {
	
	/** Auto-generated. */
	private static final long serialVersionUID = -6730725189246949458L;
	/** The user's username. */
	private final String username;
	/** The secure hash of a user's password. */
	private final byte[] passwordHash;
	/**
	 * A random {@code byte[]} that will identify a user to the server for that
	 * instance.
	 */
	private byte[] userInstance;
	
	/**
	 *
	 * @param username
	 *            The username of the user.
	 * @param passwordHash
	 *            The secure hash of a user's password.
	 */
	public User(String username, byte[] passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public byte[] getPasswordHash() {
		return this.passwordHash;
	}
	
	public byte[] getUserInstance() {
		return this.userInstance;
	}
	
	public void setUserInstance(byte[] userInstance) {
		this.userInstance = userInstance;
	}
	
}
