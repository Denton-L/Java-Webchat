package webchat.users.networking.server;

import java.io.Serializable;
import java.security.SecureRandom;

import webchat.users.PasswordManager;
import webchat.users.User;
import webchat.users.networking.UserInterface;

/**
 * A class which handles the logic of user processing.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class UserService implements UserInterface, Serializable {
	
	/** The length of the uniquely identifying {@code byte[]}. */
	private final static int USER_INSTANCE_LENGTH = 0xFF;
	
	/**
	 * Returns a {@code User} from a {@code username} and {@code password}. Note
	 * that the password is hashed automatically.
	 * 
	 * @param username
	 *            The username.
	 * @param passwordHash
	 *            A password hash which will be further hashed.
	 * @return A {@code User} from the arguments provided.
	 */
	private User userFromNameAndPassword(String username, byte[] passwordHash) {
		return new User(username, PasswordManager.serverHash(passwordHash,
				username));
	}
	
	@Override
	public boolean register(String username, byte[] passwordHash) {
		User user = userFromNameAndPassword(username, passwordHash);
		if (true /* doesn't exist in database */) {
			// add to database
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public byte[] signIn(String username, byte[] passwordHash) {
		User user = userFromNameAndPassword(username, passwordHash);
		byte[] userInstance = new byte[USER_INSTANCE_LENGTH];
		// get user from database
		new SecureRandom().nextBytes(userInstance);
		// store userInstance in database
		return userInstance;
	}
	
	@Override
	public void logout(byte[] userInstance) {
		// remove userInstance from database
		
	}
	
}
