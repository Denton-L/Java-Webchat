package webchat.users.networking.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import webchat.networking.GenericClient;
import webchat.users.PasswordManager;
import webchat.users.networking.UserInterface;
import webchat.users.networking.server.UserServer;

public class UserClient extends GenericClient {
	private UserInterface userInterface;
	private byte[] userInstance;
	
	public UserClient(String serverURL) throws MalformedURLException,
			NotBoundException, RemoteException {
		super(serverURL, UserServer.URL_LOCATION);
	}
	
	public boolean register(String username, byte[] password) {
		byte[] hashedPass = PasswordManager.clientHash(password, username);
		PasswordManager.clearArray(password);
		return userInterface.register(username, hashedPass);
	}
	
	public byte[] signIn(String username, byte[] password) {
		byte[] hashedPass = PasswordManager.clientHash(password, username);
		PasswordManager.clearArray(password);
		return userInstance = userInterface.signIn(username, hashedPass);
	}
	
	public void logout(byte[] userInstance) {
		userInterface.logout(userInstance);
	}
}
