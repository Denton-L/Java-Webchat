package users.networking;

import java.rmi.Remote;

public interface UserInterface extends Remote {
	
	boolean register(String username, byte[] password);
	
	String signIn(String username, byte[] password);
	
	void logout(String instanceHash);
	
}
