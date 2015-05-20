package networking.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import networking.MessageInterface;
import networking.server.MessageServer;

public class MessageClient {
	
	MessageInterface messageInterface;
	Thread messageThread;
	
	public MessageClient(String serverIp) throws MalformedURLException, NotBoundException, RemoteException {
				System.setSecurityManager(new RMISecurityManager());
				messageInterface = (MessageInterface) Naming.lookup(serverIp +MessageServer.URL_LOCATION);
				
				messageThread = new Thread(new GetMessages(messageInterface));
	}
	
	public void startClient() {
		messageThread.start();
	}
}
