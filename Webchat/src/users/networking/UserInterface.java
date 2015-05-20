package users.networking;

public interface UserInterface {
	
	public boolean register(String username, byte[] password);
	
	public String signIn(String username, byte[] password);
	
}
