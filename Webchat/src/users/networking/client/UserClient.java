package users.networking.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import networking.GenericClient;
import users.networking.UserInterface;
import users.networking.server.UserServer;

public class UserClient extends GenericClient {
	private UserInterface userInterface;
	
	public UserClient(String serverIp) throws MalformedURLException,
			NotBoundException, RemoteException {
		super(serverIp, UserServer.URL_LOCATION);
	}
}
