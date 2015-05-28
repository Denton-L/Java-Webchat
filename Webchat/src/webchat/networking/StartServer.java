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

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err
					.println("Please enter the path of the database location as an argument.");
			System.exit(1);
		}
		
		try {
			UserDatabase userDatabase = new UserDatabase(new File(args[0]));
			new MessageServer(userDatabase).startServer();
			new UserServer(userDatabase).startServer();
		} catch (FileNotFoundException e) {
			System.err.println("Database could not be found.");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Database could not be loaded.");
			System.exit(1);
		} catch (AlreadyBoundException e) {
			System.err.println("Server could not be started.");
			System.exit(1);
		}
	}
}
