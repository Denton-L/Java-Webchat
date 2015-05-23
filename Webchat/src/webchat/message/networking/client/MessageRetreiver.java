package webchat.message.networking.client;

import java.util.SortedSet;

import webchat.message.Message;
import webchat.message.networking.MessageInterface;

/**
 * An implementation of {@code Runnable} which fetches messages from the server.
 * 
 * @author Denton Liu
 * @version 2015-05-23
 */
public class MessageRetreiver implements Runnable {

	/** The last message received. */
	private Message lastMessage;
	/** The {@code MessageInterface} from the server which will be called. */
	private MessageInterface messageInterface;
	/** The time in milliseconds between successive message fetches. */
	private long period;
	/** Indicates whether the thread is running. */
	private volatile boolean threadRunning;

	/**
	 * 
	 * @param messageInterface
	 *            The {@code MessageInterface} which is provided by the server.
	 * @param period
	 *            The time in milliseconds between successive message fetches.
	 */
	public MessageRetreiver(MessageInterface messageInterface, long period) {
		this.lastMessage = null;
		this.messageInterface = messageInterface;
		this.period = period;
		this.threadRunning = true;
	}

	/**
	 * Throwing an {@code InterruptedException} will cause the thread to refresh
	 * instantaneously.
	 */
	@Override
	public void run() {
		while (threadRunning) {
			SortedSet<Message> messages = messageInterface.pull(lastMessage);
			lastMessage = messages.last();
			// TODO: GUI handler code here with messages
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	/**
	 * Halts the thread on the next loop iteration.
	 */
	public synchronized void stopThread() {
		this.threadRunning = false;
	}
}
