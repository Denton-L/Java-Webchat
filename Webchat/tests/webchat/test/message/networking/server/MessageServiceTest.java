package webchat.test.message.networking.server;

import java.io.File;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import webchat.database.UserDatabase;
import webchat.message.networking.server.MessageService;

public class MessageServiceTest extends TestCase {
	MessageService service;

	@Override
	protected void setUp() throws RemoteException, Exception {
		final UserDatabase database = new UserDatabase(
				new File("/database.ser"));
		this.service = new MessageService(database);
	}

	public void testPull() {
		// TODO figure out testing here
	}

}
