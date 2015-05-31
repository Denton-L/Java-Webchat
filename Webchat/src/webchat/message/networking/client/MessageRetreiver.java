package webchat.message.networking.client;

import java.rmi.RemoteException;
import java.util.SortedSet;

import webchat.message.Message;
import webchat.message.networking.MessageInterface;
import webchat.ui.ClientUI;

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
	
	private ClientUI ui;
	
	/**
	 * 
	 * @param messageInterface
	 *            The {@code MessageInterface} which is provided by the server.
	 * @param period
	 *            The time in milliseconds between successive message fetches.
	 */
	public MessageRetreiver(MessageInterface messageInterface, long period, ClientUI ui) {
		this.lastMessage = null;
		this.messageInterface = messageInterface;
		this.period = period;
		this.threadRunning = true;
		this.ui = ui;
	}
	
	/**
	 * Throwing an {@code InterruptedException} will cause the thread to refresh
	 * instantaneously.
	 */
	@Override
	public void run(){
		while (threadRunning) {
			SortedSet<Message> messages;
			try {
				messages = messageInterface.pull(lastMessage);
			} catch (RemoteException e) {
				messages = null;
				e.printStackTrace();
			}
			lastMessage = messages.last();
			ui.writeMsg(messages);
			System.out.println(lastMessage.getContent());
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
