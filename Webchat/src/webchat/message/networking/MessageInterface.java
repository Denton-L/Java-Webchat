package webchat.message.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.SortedSet;

import webchat.message.Message;

/**
 * An abstract interface which will allow messages to be sent between the client
 * and the server through the RMI protocol.
 *
 * @author Denton Liu
 * @author Filip Francetic
 * @version 2015-05-23
 */
public interface MessageInterface extends Remote {
	
	/**
	 * Pushes a message to the server.
	 *
	 * @param content
	 *            The message content that will be pushed.
	 * @param userInstance
	 *            The {@code byte[]} which uniquely identifies a user.
	 * @throws RemoteException
	 * @throws NotLoggedInException
	 *             Thrown when invalid an invalid {@code userInstance} has been
	 *             passed.
	 */
	void push(String content, byte[] userInstance) throws RemoteException,
			NotLoggedInException;
	
	/**
	 * Pulls all messages received since the last message received.
	 *
	 * @param lastMessageReceived
	 *            The {@code Message} which was the last one received by the
	 *            client. Pass {@code null} if no messages have been received
	 *            yet.
	 * @return A {@code SortedSet<Message>} ordered in ascending order of their
	 *         timestamps from when the last message was received.
	 * @throws RemoteException
	 */
	SortedSet<Message> pull(Message lastMessageReceived) throws RemoteException;
}
