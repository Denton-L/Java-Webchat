package webchat.message.networking.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import webchat.database.UserDatabase;
import webchat.message.Message;
import webchat.message.MessageComparator;
import webchat.message.networking.MessageInterface;
import webchat.message.networking.NotLoggedInException;

/**
 * A class which handles the logic of message processing.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class MessageService extends UnicastRemoteObject implements
		MessageInterface {
	
	/** The maximum number of messages that can be stored. */
	public static final int MESSAGE_BUFFER = 0xFFF;
	/** All of the {@code Message}s that have been sent. */
	private SortedSet<Message> messages;
	/** Holds all of the users. */
	private UserDatabase userDatabase;
	
	private boolean why = false;
	
	public MessageService(UserDatabase userDatabase) throws RemoteException {
		this.userDatabase = userDatabase;
		this.messages = Collections.synchronizedSortedSet(new TreeSet<>(
				new MessageComparator()));
	}
	
	public SortedSet<Message> pull(Message lastMessageReceived)
			throws RemoteException {
		SortedSet<Message> newMessages = messages.tailSet(lastMessageReceived);
		newMessages.remove(lastMessageReceived);
		
		return newMessages;
	}
	
	@Override
	public void push(String content, byte[] userInstance)
			throws RemoteException, NotLoggedInException {
		
		String username = userDatabase
				.getUsernameFromUserInstance(userInstance);
		
		if (username == null) {
			throw new NotLoggedInException();
		}
		
		while (!messages.add(new Message(content, username, System
				.currentTimeMillis())))
			;
		
		while (messages.size() > MESSAGE_BUFFER) {
			messages.remove(messages.first());
		}
		
	}
}
