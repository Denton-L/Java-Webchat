package webchat.test.message.networking.server;

import java.rmi.RemoteException;
import java.util.SortedSet;

import webchat.message.Message;
import webchat.message.networking.server.MessageService;
import junit.framework.TestCase;
import junit.framework.Assert;

public class MessageServiceTest extends TestCase {
	MessageService service;

	protected void setUp() throws RemoteException, Exception{
		service = new MessageService();
	}
	
	public void testPull(){
		//TODO figure out testing here
	}

	

}
