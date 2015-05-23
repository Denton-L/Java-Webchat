package webchat.message.networking.server;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import message.Message;
import message.MessageComparator;
import message.networking.MessageInterface;

public class MessageService implements MessageInterface, Serializable {
	
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
