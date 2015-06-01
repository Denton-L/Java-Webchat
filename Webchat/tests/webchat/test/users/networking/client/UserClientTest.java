package webchat.test.users.networking.client;

import java.io.File;

import junit.framework.TestCase;
import webchat.database.UserDatabase;
import webchat.users.networking.client.UserClient;
import webchat.users.networking.server.UserServer;

public class UserClientTest extends TestCase {
	UserServer server;
	UserClient client;
	boolean setUpIsDone = true;
	
	@Override
	protected void setUp() throws Exception {
		if (this.setUpIsDone) {
			final UserDatabase database = new UserDatabase(new File(
					"/database.ser"));
			final UserServer server = new UserServer(database);
			server.startServer();
			// client = new
			// UserClient(InetAddress.getLocalHost().getHostAddress());
			// TODO fix this
		}
		
		this.setUpIsDone = false;
	}
	
	public void testCreation() {
		// Note is blank because only needs to be created
	}
	
}
