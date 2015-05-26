package webchat.message.networking.server;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import webchat.message.Message;
import webchat.message.MessageComparator;
import webchat.message.networking.MessageInterface;

/**
 * A class which handles the logic of message processing.
 * 
 * @author Denton Liu
 * @version 2015-05-25
 */
public class MessageService implements MessageInterface, Serializable {
	
	/** All of the {@code Message}s that have been sent. */
	private SortedSet<Message> messages;
	
	public MessageService() {
		messages = new TreeSet<>(new MessageComparator());
	}
	
	@Override
	public SortedSet<Message> pull(Message lastMessageReceived) {
		return messages.tailSet(lastMessageReceived);
	}
	
	@Override
	public void push(String content, byte[] userInstance) {
		// check if userInstance exists in database
		Message message = new Message(content, null /* get from database */,
				System.currentTimeMillis());
		messages.add(message);
		// add message to the database as well
		
	}
	
}
