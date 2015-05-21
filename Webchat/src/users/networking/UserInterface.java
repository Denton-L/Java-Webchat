package users.networking;

import java.rmi.Remote;

public interface UserInterface extends Remote {
	
	boolean register(String username, byte[] password);
	
	byte[] signIn(String username, byte[] password);
	
	void logout(byte[] userInstance);
	
}
