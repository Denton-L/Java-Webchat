package webchat.message.networking.server;

import java.rmi.RemoteException;

import webchat.database.UserDatabase;
import webchat.networking.GenericServer;

/**
 * A server that handles the transmission of messages.
 *
 * @author Denton Liu
 * @version 2015-05-25
 */
public class MessageServer extends GenericServer {

	/** The location of this class on the server. */
	public static final String URL_LOCATION = "messages";

	/**
	 * Creates a new {@code MessageServer} with the {@code UserDatabase} as
	 * specified.
	 *
	 * @param userDatabase
	 *            The {@code UserDatabase} which this server is based on.
	 */
	public MessageServer(UserDatabase userDatabase) throws RemoteException {
		super(URL_LOCATION, new MessageService(userDatabase));
	}
}