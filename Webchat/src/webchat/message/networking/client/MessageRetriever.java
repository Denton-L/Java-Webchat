package webchat.message.networking.client;

import java.rmi.RemoteException;
import java.util.SortedSet;

import webchat.message.Message;
import webchat.message.networking.MessageInterface;
import webchat.networking.GenericRetriever;
import webchat.ui.ClientUI;

/**
 * An implementation of {@code Runnable} which fetches messages from the server.
 * 
 * @author Denton Liu
 * @version 2015-05-23
 */
public class MessageRetriever extends GenericRetriever {

	/** The last message received. */
	private Message lastMessage;
	/** The {@code MessageInterface} from the server which will be called. */
	private MessageInterface messageInterface;

	/**
	 * 
	 * @param messageInterface
	 *            The {@code MessageInterface} which is provided by the server.
	 * @param period
	 *            The time in milliseconds between successive message fetches.
	 */
	public MessageRetriever(MessageInterface messageInterface, long period,
			ClientUI ui) {
		super(period, ui);
		this.messageInterface = messageInterface;
		this.lastMessage = null;
	}

	@Override
	public void retrieve() {
		SortedSet<Message> messages;
		try {
			messages = messageInterface.pull(lastMessage);
		} catch (RemoteException e) {
			messages = null;
			e.printStackTrace();
			System.out.println("test");
		}
		if (messages != null) {
			if (messages.size() > 0) {
				lastMessage = messages.last();
				ui.writeMsg(messages);
			}
		}
	}

}
