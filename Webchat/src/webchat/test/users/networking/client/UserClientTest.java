package webchat.test.users.networking.client;

import java.io.File;
import java.net.InetAddress;

import webchat.networking.StartServer;
import webchat.users.networking.client.UserClient;
import webchat.users.networking.server.UserServer;
import junit.framework.TestCase;

public class UserClientTest extends TestCase {
	UserServer server;
	UserClient client;
	boolean setUpIsDone = true;
	
	protected void setUp() throws Exception {
		if (setUpIsDone){
			StartServer.start(new File("/res/database.ser"));
			client = new UserClient(InetAddress.getLocalHost().getHostAddress());
		}
		
		setUpIsDone = false;
	}
	
	public void testCreation(){
		//Note is blank because only needs to be created
	}

	

}
