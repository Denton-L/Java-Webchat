package message.networking.client;

import java.util.SortedSet;

import message.Message;
import message.networking.MessageInterface;

public class MessageRetriever implements Runnable {

	private Message message;
	private MessageInterface messageInterface;
	private long period;
	private volatile boolean threadRunning;

	public MessageRetriever(MessageInterface messageInterface, long period) {
		this.message = null;
		this.messageInterface = messageInterface;
		this.period = period;
		this.threadRunning = true;
	}

	@Override
	public void run() {
		while (threadRunning) {
			SortedSet<Message> messages = messageInterface.pull(message);
			message = messages.last();
			// TODO: GUI handler code here with messages
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}
	
	public synchronized void stopThread() {
		this.threadRunning = false;
	}
}
