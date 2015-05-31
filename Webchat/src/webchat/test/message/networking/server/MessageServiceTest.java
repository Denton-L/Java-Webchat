package webchat.test.message.networking.server;

import java.io.File;
import java.rmi.RemoteException;
import java.util.SortedSet;

import webchat.database.UserDatabase;
import webchat.message.Message;
import webchat.message.networking.server.MessageService;
import junit.framework.TestCase;
import junit.framework.Assert;

public class MessageServiceTest extends TestCase {
	MessageService service;

	protected void setUp() throws RemoteException, Exception{
		UserDatabase database = new UserDatabase(new File("/res/database.ser"));
		service = new MessageService(database);
	}
	
	public void testPull(){
		//TODO figure out testing here
	}

	

}
