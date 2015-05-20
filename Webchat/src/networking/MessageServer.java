package networking;

import java.util.SortedSet;
import java.util.TreeSet;

public class MessageServer {
	
	SortedSet<Message> messages;
	
	public MessageServer() {
		messages = new TreeSet<>(new MessageComparator());
	}
	
	public SortedSet<Message> getMessagesSince(Message lastMessageRecieved) {
		return messages.tailSet(lastMessageRecieved);
	}
}