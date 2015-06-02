package webchat.test.users.networking.client;

import java.io.File;
import java.rmi.RemoteException;


import org.jmock.integration.junit3.MockObjectTestCase;
import org.jmock.lib.legacy.ClassImposteriser;
import org.jmock.Mockery;

import webchat.database.UserDatabase;
import webchat.ui.ClientUI;
import webchat.users.networking.client.UserClient;
import webchat.users.networking.server.UserServer;

public class UserClientTest extends MockObjectTestCase {
	Mockery context = new Mockery();
	
	ClientUI ui;
	UserClient client;
	UserServer server;
	UserDatabase database;
	private File file;
	private boolean notSetUp = true;
	
	private String username = "test";
	private byte[] passwordHash = {0,0,0};
	private byte[] userInstance;
	
	protected void setUp() throws RemoteException, Exception {
		if (notSetUp) {
			context.setImposteriser(ClassImposteriser.INSTANCE);
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
		this.server = null;
		this.database = null;
		this.userInstance = null;
	}
	
	
	public void testLogOut(){
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
		try {
			client.logout(userInstance);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
	}

}
