package users.networking.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import networking.GenericClient;
import users.PasswordManager;
import users.networking.UserInterface;
import users.networking.server.UserServer;

public class UserClient extends GenericClient {
	private UserInterface userInterface;
	private byte[] userInstance;
	
	public UserClient(String serverIp) throws MalformedURLException,
			NotBoundException, RemoteException {
		super(serverIp, UserServer.URL_LOCATION);
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
