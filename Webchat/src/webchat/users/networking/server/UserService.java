package webchat.users.networking.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;

import webchat.database.UserDatabase;
import webchat.users.PasswordManager;
import webchat.users.networking.UserInterface;

/**
 * A class which handles the logic of user processing.
 *
 * @author Denton Liu
 * @version 2015-05-25
 */
public class UserService extends UnicastRemoteObject implements UserInterface {

	/** Auto-generated. */
	private static final long serialVersionUID = -7451365201964914848L;
	
	/** The length of the uniquely identifying {@code byte[]}. */
	private final static int USER_INSTANCE_LENGTH = 0xFF;

	/** The {@code UserDatabase} which this {@code UserService} is based on. */
	private final UserDatabase userDatabase;

	/**
	 *
	 * @param userDatabase
	 *            The {@code UserDatabase} which this {@code UserService} is
	 *            based on.
	 */
	public UserService(UserDatabase userDatabase) throws RemoteException {
		this.userDatabase = userDatabase;
	}

	@Override
	public boolean register(String username, byte[] passwordHash)
			throws RemoteException {
		final byte[] hashedPassword = PasswordManager.serverHash(passwordHash,
				username);
		final boolean result = this.userDatabase.addUser(username,
				hashedPassword);
		PasswordManager.clearArray(hashedPassword);
		return result;

	}

	@Override
	public byte[] signIn(String username, byte[] passwordHash)
			throws RemoteException {

		byte[] userInstance = new byte[USER_INSTANCE_LENGTH];
		final byte[] hashedPassword = PasswordManager.serverHash(passwordHash,
				username);
		if (this.userDatabase
				.isCorrectUserAndPassword(username, hashedPassword)) {
			new SecureRandom().nextBytes(userInstance);
			this.userDatabase.setUserInstance(username, userInstance);
		} else {
			userInstance = null;
		}
		PasswordManager.clearArray(hashedPassword);
		return userInstance;
	}

	@Override
	public void logout(byte[] userInstance) throws RemoteException {
		this.userDatabase.setUserInstance(
				this.userDatabase.getUsernameFromUserInstance(userInstance),
				null);

	}

	@Override
	public String[] getOtherOnlineUsers(String[] users) {
		return this.userDatabase.getOtherUsersWithUserInstance(users);
	}
}
