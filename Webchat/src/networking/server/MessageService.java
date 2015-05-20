package networking.server;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import networking.Message;
import networking.MessageComparator;
import networking.MessageInterface;

public class MessageService implements MessageInterface, Serializable {
	
	SortedSet<Message> messages;
	
	public MessageService() {
		messages = new TreeSet<>(new MessageComparator());
	}
	
	@Override
	public SortedSet<Message> pull(Message lastMessageRecieved) {
		return messages.tailSet(lastMessageRecieved);
	}

	@Override
	public void push(Message message) {
		// TODO Auto-generated method stub
	}
	
}
