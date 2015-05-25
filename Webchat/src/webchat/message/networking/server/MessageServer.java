package webchat.message.networking.server;

import webchat.networking.GenericServer;

/**
 * A server that handles the transmission of messages.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class MessageServer extends GenericServer {
	
	/** The subdomain location of the server. */
	public static final String URL_LOCATION = "messages";
	
	public MessageServer() {
		super(URL_LOCATION, new MessageService());
	}
}