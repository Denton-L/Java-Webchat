package webchat.message.networking.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import message.networking.MessageInterface;
import message.networking.server.MessageServer;
import networking.GenericClient;

/**
 * The client which will communicate with {@code MessageServer}.
 * 
 * @author Filip Francetic
 * @author Denton Liu
 * @verion 2015-05-23
 */
public class MessageClient extends GenericClient {

	/**
	 * The {@code MessageInterface} which will be used to communicate with the
	 * client.
	 */
	private MessageInterface messageInterface;
	/** The Runnable which will contain the logic to fetching messages. */
	private MessageRetreiver messageRetreiver;
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
	 * @param serverIp
	 *            The URL of the server.
	 * @param userInstance
	 *            A {@code byte[]} containing the user's unique identifier to
	 *            the server.
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public MessageClient(String serverIp, byte[] userInstance)
			throws MalformedURLException, NotBoundException, RemoteException {
		super(serverIp, MessageServer.URL_LOCATION);
		this.messageInterface = (MessageInterface) remoteInterface;
		this.userInstance = userInstance;
		messageRetreiver = new MessageRetreiver(messageInterface, REFRESH_RATE);
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
	 * @param message The content of the message which will be pushed.
	 */
	public void sendMessage(String message) {
		messageInterface.push(message, userInstance);
	}
}
