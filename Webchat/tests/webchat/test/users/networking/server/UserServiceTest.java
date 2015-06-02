package webchat.test.users.networking.server;

import java.io.File;
import java.rmi.RemoteException;

import junit.framework.TestCase;
import webchat.database.UserDatabase;
import webchat.users.networking.server.UserService;

public class UserServiceTest extends TestCase {
	private File file;
	private boolean notSetUp = true;
	private UserDatabase database;
	private UserService service;
	
	private String username = "test";
	private byte[] passwordHash = {0,0,0};
	private byte[] userInstance;
	
	protected void setUp() throws RemoteException, Exception {
		if (notSetUp) {
			file = new File("testFile.ser");
			file.deleteOnExit();
			new UserDatabase().saveDatabase(file);
		}
		this.database = new UserDatabase(file);
		this.service = new UserService(database);
		notSetUp = true;
	}
	
	protected void tearDown() {
		this.service = null;
		this.database = null;
		this.userInstance = null;
	}
	 
	
	public void testUsersOnlineAndLogin(){
		for (int i = 0; i<service.getOnlineUsers().length; i++)
			assertSame(new String[] {}[i], service.getOnlineUsers()[i]);
		try {
			assertTrue(service.register(username, passwordHash));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		try {
			userInstance = service.signIn(username, passwordHash);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		for (int i = 0; i<service.getOnlineUsers().length; i++)
			assertSame(new String[] {"test"}[i], service.getOnlineUsers()[i]);
		
	}
	
	public void testLogOut(){
		try {
			assertTrue(service.register(username, passwordHash));
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		try {
			userInstance = service.signIn(username, passwordHash);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		try {
			service.logout(userInstance);
		} catch (RemoteException e) {
			e.printStackTrace();
			fail();
		}
		for (int i = 0; i<service.getOnlineUsers().length; i++)
			assertSame(new String[] {}[i], service.getOnlineUsers()[i]);
	}

}
