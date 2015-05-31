package webchat.message.networking.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import webchat.database.UserDatabase;
import webchat.message.Message;
import webchat.message.MessageComparator;
import webchat.message.networking.MessageInterface;

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
	
	public MessageService(UserDatabase userDatabase) throws RemoteException {
		this.userDatabase = userDatabase;
		this.messages = Collections.synchronizedSortedSet(new TreeSet<>(
				new MessageComparator()));
	}
	
	@Override
	public SortedSet<Message> pull(Message lastMessageReceived)
			throws RemoteException {
		SortedSet<Message> newMessages = messages.tailSet(lastMessageReceived);
		//System.out.println(newMessages.isEmpty());
		if (!(newMessages.isEmpty() || lastMessageReceived == null)) {
			newMessages.remove(newMessages.first());
		}
		return newMessages;
	}
	
	@Override
	public void push(String content, byte[] userInstance)
			throws RemoteException {
		
		String username = userDatabase
				.getUsernameFromUserInstance(userInstance);
		
		if (username == null) {
			return;
		}
		
		while (!messages.add(new Message(content, username, System
				.currentTimeMillis())))
			;
		
		while (messages.size() > MESSAGE_BUFFER) {
			messages.remove(messages.first());
		}
		
	}
}
