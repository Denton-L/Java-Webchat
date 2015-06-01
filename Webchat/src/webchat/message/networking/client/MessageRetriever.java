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
	private final MessageInterface messageInterface;

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
			messages = this.messageInterface.pull(this.lastMessage);
		} catch (final RemoteException e) {
			messages = null;
			e.printStackTrace();
		}
		if (messages != null) {
			if (!messages.isEmpty()) {
				this.lastMessage = messages.last();
				this.ui.writeMsg(messages);
			}
		}
	}

}
