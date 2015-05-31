package webchat.users.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

import webchat.users.User;

/**
 * An abstract interface which will allow user related operations to
 * occur through the RMI protocol.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public interface UserInterface extends Remote {
	
	/**
	 * Causes a user to be registered with the username and password hash
	 * specified.
	 * 
	 * @param username
	 *            The username to be registered.
	 * @param passwordHash
	 *            The password after it has been hashed.
	 * @return {@code true} if the registration was successful, otherwise
	 *         {@code false}.
	 * @throws RemoteException TODO
	 */
	boolean register(String username, byte[] passwordHash) throws RemoteException;
	
	/**
	 * Signs in a user and returns a {@code byte[]} which will be
	 * used to uniquely identify the user to the server.
	 * 
	 * @param username
	 *            The username to sign in.
	 * @param passwordHash
	 *            The password after it has been hashed.
	 * @return {@code null} if invalid sign in otherwise a {@code byte[]} which
	 *         uniquely identifies the user
	 * @throws RemoteException TODO
	 */
	byte[] signIn(String username, byte[] passwordHash) throws RemoteException;
	
	/**
	 * Causes the identifying {@code byte[]} to become removed from the database
	 * and blocking further communication using that identifier.
	 * 
	 * @param userInstance
	 *            The {@code byte[]} which will identify a user.
	 * @throws RemoteException TODO
	 */
	void logout(byte[] userInstance) throws RemoteException;
	
	/**
	 * Gets a list of users online.
	 * 
	 * @return The names of all users online.
	 * @throws RemoteException
	 */
	String[] getOnlineUsers() throws RemoteException;
	
}
