package message.networking;

import java.rmi.Remote;
import java.util.SortedSet;

import message.Message;

public interface MessageInterface extends Remote {
	public void push(String message, String userHash);
	
	public SortedSet<Message> pull(Message lastMessageRecieved);
}
