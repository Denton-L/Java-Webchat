package webchat.users.networking.server;

import java.rmi.RemoteException;

import webchat.database.UserDatabase;
import webchat.networking.GenericServer;

/**
 * A server which handles user actions.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class UserServer extends GenericServer {

	/**
	 * Creates a new {@code} UserServer with the {@code UserDatabase} as
	 * specified.
	 * 
	 * @param userDatabase
	 *            The {@code UserDatabase} which this server is based on.
	 */
	public UserServer(UserDatabase userDatabase) throws RemoteException {
		super(new UserService(userDatabase));
		setLocation("users");
	}
}