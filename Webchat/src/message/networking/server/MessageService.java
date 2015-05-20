package message.networking.server;

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
	public SortedSet<Message> pull(Message lastMessageRecieved) {
		return messages.tailSet(lastMessageRecieved);
	}

	@Override
	public void push(String message, String userHash) {
		// TODO Auto-generated method stub
		
	}
	
}
