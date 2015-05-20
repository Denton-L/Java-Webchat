package message.networking;

import java.rmi.Remote;
import java.util.SortedSet;

import message.Message;

public interface MessageInterface extends Remote {
	void push(String message, String userHash);
	
	SortedSet<Message> pull(Message lastMessageRecieved);
}
