package message.networking.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import message.Message;
import message.networking.MessageInterface;
import message.networking.server.MessageServer;

public class MessageClient {
	
	MessageInterface messageInterface;
	Thread messageThread;
	String userHash;
	
	public MessageClient(String serverIp, String userHash)
			throws MalformedURLException, NotBoundException, RemoteException {
		this.userHash = userHash;
		this.messageThread = new Thread(new MessageRetriever(messageInterface));
		
		System.setSecurityManager(new RMISecurityManager());
		this.messageInterface = (MessageInterface) Naming.lookup(serverIp
				+ MessageServer.URL_LOCATION);
	}
	
	public void startClient() {
		messageThread.start();
	}
	
	public void sendMessage(String message) {
		messageInterface.push(message, userHash);
	}
}
