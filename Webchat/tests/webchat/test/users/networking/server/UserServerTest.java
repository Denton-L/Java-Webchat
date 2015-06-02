package webchat.test.users.networking.server;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import webchat.database.UserDatabase;
import webchat.users.networking.server.UserServer;
import junit.framework.TestCase;

public class UserServerTest extends TestCase {
	UserServer server;
	protected void setUp() throws Exception {
		File file = new File("testFile.ser");
		file.deleteOnExit();
		new UserDatabase().saveDatabase(file);
		final UserDatabase database = new UserDatabase(file);
		this.server = new UserServer(database);
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
