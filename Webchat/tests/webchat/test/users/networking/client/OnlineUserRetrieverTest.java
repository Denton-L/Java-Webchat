package webchat.test.users.networking.client;

import java.io.File;
import java.rmi.RemoteException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.jmock.integration.junit3.MockObjectTestCase;

import webchat.database.UserDatabase;
import webchat.ui.ClientUI;
import webchat.users.networking.client.UserClient;
import webchat.users.networking.server.UserServer;
import junit.framework.TestCase;

public class OnlineUserRetrieverTest extends MockObjectTestCase {

	Mockery context = new Mockery();

	ClientUI ui;
	UserClient client;
	UserServer server;
	UserDatabase database;
	private File file;
	private boolean notSetUp = true;

	private String username = "test";
	private String[] expected = {"test"};
	private byte[] passwordHash = { 0, 0, 0 };
	private byte[] userInstance;

	protected void setUp() throws RemoteException, Exception {
		if (notSetUp) {
			context.setImposteriser(ClassImposteriser.INSTANCE);
			context.setThreadingPolicy(new Synchroniser());
			ui = context.mock(ClientUI.class);
			file = new File("testFile.ser");
			file.deleteOnExit();
			new UserDatabase().saveDatabase(file);
		}
		this.database = new UserDatabase(file);
		this.server = new UserServer(database);
		server.startServer();
		client = new UserClient("", ui);

		notSetUp = true;
	}

	protected void tearDown() {
		client.stopClient();
		this.server = null;
		this.database = null;
		this.userInstance = null;
	}

	public void testRecieveMessages() {
		try {
			assertTrue(client.register(username, passwordHash));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		try {
			userInstance = client.signIn(username, passwordHash);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}

		checking(new Expectations() {
			{
				oneOf(ui).writeUsers(expected);
			}
		});

		client.startClient();
		
		client.refreshUsers();
	}

}
