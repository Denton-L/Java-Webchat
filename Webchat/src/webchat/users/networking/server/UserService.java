package webchat.users.networking.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.security.SecureRandom;

import webchat.database.UserDatabase;
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

	/** The {@code UserDatabase} which this {@code UserService} is based on. */
	private UserDatabase userDatabase;

	/**
	 * 
	 * @param userDatabase
	 *            The {@code UserDatabase} which this {@code UserService} is
	 *            based on.
	 */
	public UserService(UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

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
	public boolean register(String username, byte[] passwordHash)
			throws RemoteException {
		User user = userFromNameAndPassword(username, passwordHash);
		return userDatabase.addUser(username, passwordHash);

	}

	@Override
	public byte[] signIn(String username, byte[] passwordHash)
			throws RemoteException {

		byte[] userInstance = new byte[USER_INSTANCE_LENGTH];

		if (userDatabase.isCorrectUserAndPassword(username, passwordHash)) {
			new SecureRandom().nextBytes(userInstance);
			userDatabase.setUserInstance(username, userInstance);
			return userInstance;
		} else {
			return null;
		}
	}

	@Override
	public void logout(byte[] userInstance) throws RemoteException {
		userDatabase.setUserInstance(
				userDatabase.getUsernameFromUserInstance(userInstance), null);

	}

}
