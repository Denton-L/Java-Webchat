package webchat.test.message.networking.server;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import webchat.database.UserDatabase;
import webchat.message.networking.server.MessageServer;
import junit.framework.TestCase;

public class MessageServerTest extends TestCase {
	MessageServer server;

	protected void setUp() throws Exception{
		try {
			UserDatabase database = new UserDatabase(new File("/res/database.ser"));
			server = new MessageServer(database);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	public void testServer(){
		try {
			server.startServer();
		} catch (MalformedURLException | RemoteException
				| AlreadyBoundException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

}
