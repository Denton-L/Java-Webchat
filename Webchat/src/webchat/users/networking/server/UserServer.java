package webchat.users.networking.server;

import webchat.networking.GenericServer;

/**
 * A server which handles user actions.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class UserServer extends GenericServer {
	
	public static final String URL_LOCATION = "users";
	
	public UserServer() {
		super(new UserService());
	}
}