package webchat.users.networking.client;

import java.rmi.RemoteException;

import webchat.networking.GenericRetriever;
import webchat.ui.ClientUI;
import webchat.users.networking.UserInterface;

public class OnlineUserRetriever extends GenericRetriever {
	
	private final UserInterface userInterface;
	
	public OnlineUserRetriever(UserInterface userInterface, long period,
			ClientUI ui) {
		super(period, ui);
		this.userInterface = userInterface;
	}
	
	@Override
	protected void retrieve() {
		try {
			this.ui.writeUsers(this.userInterface.getOtherOnlineUsers(this.ui
					.getUsers()));
		} catch (final RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}
