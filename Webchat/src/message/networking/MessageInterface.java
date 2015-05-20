package message.networking;

import java.rmi.Remote;
import java.util.SortedSet;

import message.Message;

public interface MessageInterface extends Remote {
	void push(String content, byte[] userInstance);
	
	SortedSet<Message> pull(Message lastMessageRecieved);
}
