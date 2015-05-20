package message.networking.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import message.networking.MessageInterface;
import message.networking.server.MessageServer;
import networking.GenericClient;

public class MessageClient extends GenericClient {
	
	private MessageInterface messageInterface;
	private Thread messageThread;
	private byte[] userInstance;
	
	public MessageClient(String serverIp, byte[] userInstance)
			throws MalformedURLException, NotBoundException, RemoteException {
		super(serverIp, MessageServer.URL_LOCATION);
		this.messageInterface = (MessageInterface) remoteInterface;
		this.userInstance = userInstance;
		this.messageThread = new Thread(new MessageRetriever(messageInterface));
	}
	
	public void startClient() {
		messageThread.start();
	}
	
	public void sendMessage(String message) {
		messageInterface.push(message, userInstance);
	}
}
