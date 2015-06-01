package webchat.message.networking.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import webchat.message.networking.MessageInterface;
import webchat.message.networking.NotLoggedInException;
import webchat.message.networking.server.MessageServer;
import webchat.networking.GenericClient;
import webchat.ui.ClientUI;

/**
 * The client which will communicate with {@code MessageServer}.
 * 
 * @author Filip Francetic
 * @author Denton Liu
 * @version 2015-05-23
 */
public class MessageClient extends GenericClient {
	
	/**
	 * The {@code MessageInterface} which will be used to communicate with the
	 * server.
	 */
	private MessageInterface messageInterface;
	/** The Runnable which will contain the logic to fetching messages. */
	private MessageRetriever messageRetreiver;
	/** A {@code Thread} which will be running to fetch messages. */
	private Thread messageThread;
	/**
	 * A {@code byte[]} which will contain the user's unique identifier to the
	 * server.
	 */
	private byte[] userInstance;
	
	/** The time in milliseconds between sucessive message pulls. */
	public static final long REFRESH_RATE = 50;
	
	/**
	 * 
	 * @param serverURL
	 *            The URL of the server.
	 * @param userInstance
	 *            A {@code byte[]} containing the user's unique identifier to
	 *            the server.
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public MessageClient(String serverURL, byte[] userInstance, ClientUI ui)
			throws MalformedURLException, NotBoundException, RemoteException {
		super(serverURL, MessageServer.URL_LOCATION);
		this.messageInterface = (MessageInterface) remoteInterface;
		this.userInstance = userInstance;
		messageRetreiver = new MessageRetriever(messageInterface, REFRESH_RATE, ui);
		this.messageThread = new Thread(messageRetreiver);
	}
	
	/**
	 * Starts the client's thread.
	 */
	public void startClient() {
		messageThread.start();
	}
	
	/**
	 * Stops the client's thread safely.
	 */
	public void stopClient() {
		messageRetreiver.stopThread();
	}
	
	/**
	 * Pushes a message to the server.
	 * 
	 * @param message
	 *            The content of the message which will be pushed.
	 * @throws NotLoggedInException 
	 */
	public void sendMessage(String message) throws RemoteException, NotLoggedInException{
		try {
			messageInterface.push(message, userInstance);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
