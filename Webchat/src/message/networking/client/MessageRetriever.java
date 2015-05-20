package message.networking.client;

import java.util.SortedSet;

import message.Message;
import message.networking.MessageInterface;

public class MessageRetriever implements Runnable {
	
	private Message message;
	private MessageInterface messageInterface;
	
	public MessageRetriever(MessageInterface messageInterface) {
		this.message = null;
		this.messageInterface = messageInterface;
	}
	
	@Override
	public void run() {
		while (true) {
			SortedSet<Message> messages = messageInterface.pull(message);
			message = messages.last();
			//TODO: GUI handler code here with messages
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
}
