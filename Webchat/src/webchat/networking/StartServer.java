package webchat.networking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;

import webchat.database.UserDatabase;
import webchat.message.networking.server.MessageServer;
import webchat.users.networking.server.UserServer;

/**
 * Creates and starts the servers so they can receive message and user requests.
 * The location of the database should be passed as an argument.
 *
 * @author Denton Liu
 * @version 2015-05-28
 */
public class StartServer {
	
	public static void start(File file) throws FileNotFoundException,
			ClassNotFoundException, IOException, AlreadyBoundException {
		final UserDatabase userDatabase = new UserDatabase(file);
		new MessageServer(userDatabase).startServer();
		new UserServer(userDatabase).startServer();
	}
}
