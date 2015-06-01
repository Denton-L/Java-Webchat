package webchat.test.message.networking.server;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import webchat.database.UserDatabase;
import webchat.message.networking.server.MessageServer;

public class MessageServerTest extends TestCase {
	MessageServer server;
	
	@Override
	protected void setUp() throws Exception {
		try {
			final UserDatabase database = new UserDatabase(new File(
					"/database.ser"));
			this.server = new MessageServer(database);
		} catch (final RemoteException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	public void testServer() {
		try {
			this.server.startServer();
		} catch (MalformedURLException | RemoteException
				| AlreadyBoundException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
}
