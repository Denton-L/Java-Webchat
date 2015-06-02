package webchat.networking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import webchat.database.UserDatabase;
import webchat.message.networking.server.MessageServer;
import webchat.users.networking.server.UserServer;

/**
 * Creates and starts the servers so they can receive message and user requests.
 * The location of the database should be passed as an argument.
 *
 * @author Denton Liu
 * @author Filip Francetic
 * @version 2015-05-28
 */


public class StartServer {

	private static UserServer userServer;
	private static MessageServer messageServer;
	
	public static void start(File file) throws FileNotFoundException,
			ClassNotFoundException, IOException, AlreadyBoundException {
		final UserDatabase userDatabase = new UserDatabase(file);
		messageServer = new MessageServer(userDatabase);
		messageServer.startServer();
		userServer = new UserServer(userDatabase);
		userServer.startServer();
	}
	
	public static void stop() throws AccessException, RemoteException, NotBoundException {
		userServer.shutDown();
		messageServer.shutDown();
	}
}
