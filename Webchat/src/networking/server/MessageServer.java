package networking.server;

import java.util.SortedSet;
import java.util.TreeSet;

import networking.Message;
import networking.MessageComparator;

public class MessageServer {
	
	SortedSet<Message> messages;
	
	public MessageServer() {
		messages = new TreeSet<>(new MessageComparator());
	}
	
	public SortedSet<Message> getMessagesSince(Message lastMessageRecieved) {
		return messages.tailSet(lastMessageRecieved);
	}
}