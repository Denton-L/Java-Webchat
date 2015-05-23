package webchat.message.networking.server;

import webchat.networking.GenericServer;

public class MessageServer extends GenericServer {
	
	public static final String URL_LOCATION = "messages";
	
	public MessageServer() {
		super(URL_LOCATION, new MessageService());
	}
}