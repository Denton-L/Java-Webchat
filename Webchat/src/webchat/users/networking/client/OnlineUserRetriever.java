package webchat.users.networking.client;

import java.rmi.RemoteException;

import webchat.networking.GenericRetriever;
import webchat.ui.ClientUI;
import webchat.users.networking.UserInterface;

/**
 * An implemention of {@code Runnable} which fetches online users from the
 * server.
 * 
 * @author Denton Liu
 * @version 2015-06-01
 */
public class OnlineUserRetriever extends GenericRetriever {
	
	/** The {@code UserInterface} which will be called. */
	private final UserInterface userInterface;
	
	/**
	 * 
	 * @param userInterface
	 *            The {@code UserInterface} which will be called.
	 * @param period
	 *            The time in milliseconds between successive message fetches.
	 * @param ui
	 *            The UI which will be updated.
	 */
	public OnlineUserRetriever(UserInterface userInterface, long period,
			ClientUI ui) {
		super(period, ui);
		this.userInterface = userInterface;
	}
	
	@Override
	protected void retrieve() {
		try {
			this.ui.writeUsers(this.userInterface.getOnlineUsers());
		} catch (final RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}
