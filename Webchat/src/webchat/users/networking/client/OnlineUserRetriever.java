package webchat.users.networking.client;

import java.rmi.RemoteException;

import webchat.networking.GenericRetriever;
import webchat.users.networking.UserInterface;

public class OnlineUserRetriever extends GenericRetriever {
	
	private UserInterface userInterface;
	
	public OnlineUserRetriever(UserInterface userInterface, long period) {
		super(period);
		this.userInterface = userInterface;
	}
	
	@Override
	protected void retrieve() {
		//TODO: GUI code, do something with the below!!
		try {
			userInterface.getOnlineUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}
