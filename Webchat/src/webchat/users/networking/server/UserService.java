package webchat.users.networking.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
public class UserService extends UnicastRemoteObject implements UserInterface {
	
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
	public UserService(UserDatabase userDatabase) throws RemoteException {
		this.userDatabase = userDatabase;
	}
	
	@Override
	public boolean register(String username, byte[] passwordHash)
			throws RemoteException {
		byte[] hashedPassword = PasswordManager.serverHash(passwordHash,
				username);
		boolean result = userDatabase.addUser(username, hashedPassword);
		PasswordManager.clearArray(hashedPassword);
		return result;
		
	}
	
	@Override
	public byte[] signIn(String username, byte[] passwordHash)
			throws RemoteException {
		
		byte[] userInstance = new byte[USER_INSTANCE_LENGTH];
		byte[] hashedPassword = PasswordManager.serverHash(passwordHash,
				username);
		if (userDatabase.isCorrectUserAndPassword(username, hashedPassword)) {
			new SecureRandom().nextBytes(userInstance);
			userDatabase.setUserInstance(username, userInstance);
		} else {
			userInstance = null;
		}
		PasswordManager.clearArray(hashedPassword);
		return userInstance;
	}
	
	@Override
	public void logout(byte[] userInstance) throws RemoteException {
		userDatabase.setUserInstance(
				userDatabase.getUsernameFromUserInstance(userInstance), null);
		
	}
	
	@Override
	public String[] getOnlineUsers() {
		return userDatabase.getUsersWithUserInstance();
	}
}
