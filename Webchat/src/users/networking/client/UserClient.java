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
	private String instanceHash;
	
	public UserClient(String serverIp) throws MalformedURLException,
			NotBoundException, RemoteException {
		super(serverIp, UserServer.URL_LOCATION);
	}
	
	public boolean register(String username, byte[] password) {
		byte[] hashedPass = PasswordManager.clientHash(password, username);
		PasswordManager.clearArray(password);
		return userInterface.register(username, hashedPass);
	}
	
	public String signIn(String username, byte[] password) {
		byte[] hashedPass = PasswordManager.clientHash(password, username);
		PasswordManager.clearArray(password);
		return instanceHash = userInterface.signIn(username, hashedPass);
	}
	
	public void logout(String instanceHash) {
		userInterface.logout(instanceHash);
	}
}
