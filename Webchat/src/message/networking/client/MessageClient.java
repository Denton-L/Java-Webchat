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
	private byte[] instanceHash;
	
	public MessageClient(String serverIp, byte[] instanceHash)
			throws MalformedURLException, NotBoundException, RemoteException {
		super(serverIp, MessageServer.URL_LOCATION);
		this.messageInterface = (MessageInterface) remoteInterface;
		this.instanceHash = instanceHash;
		this.messageThread = new Thread(new MessageRetriever(messageInterface));
	}
	
	public void startClient() {
		messageThread.start();
	}
	
	public void sendMessage(String message) {
		messageInterface.push(message, instanceHash);
	}
}
