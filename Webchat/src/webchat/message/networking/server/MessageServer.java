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
	public static final String URL_LOCATION = "messages";
	
	
	public MessageServer(UserDatabase userDatabase) throws RemoteException {
		super(new MessageService(userDatabase));
		setLocation(URL_LOCATION);
	}
}