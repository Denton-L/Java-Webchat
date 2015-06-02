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
			File file = new File("testFile.ser");
			file.deleteOnExit();
			new UserDatabase().saveDatabase(file);
			final UserDatabase database = new UserDatabase(file);
			this.server = new MessageServer(database);
		} catch (final RemoteException e) {
			fail();
		}
	}
	
	public void testServer() {
		try {
			this.server.startServer();
		} catch (MalformedURLException | RemoteException
				| AlreadyBoundException e) {
			fail();
		}
	}
	
}
