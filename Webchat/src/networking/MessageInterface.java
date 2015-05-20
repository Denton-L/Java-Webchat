package networking;

import java.rmi.Remote;
import java.util.SortedSet;

public interface MessageInterface extends Remote {
	public void push(Message message);
	
	public SortedSet<Message> pull(Message lastMessageRecieved);
}
