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
	
	/** The location of this class on the server. */
	public static final String URL_LOCATION = "users";
	
	/**
	 * Creates a new {@code UserServer} with the {@code UserDatabase} as
	 * specified.
	 *
	 * @param userDatabase
	 *            The {@code UserDatabase} which this server is based on.
	 * @throws RemoteException
	 */
	public UserServer(UserDatabase userDatabase) throws RemoteException {
		super(URL_LOCATION, new UserService(userDatabase));
	}
}