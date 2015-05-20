package users.networking;

public interface UserInterface {
	
	boolean register(String username, byte[] password);
	
	String signIn(String username, byte[] password);
	
	void logout(String username, byte[] password);
	
}
