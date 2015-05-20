package users.networking.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import users.networking.UserInterface;
import users.networking.server.UserServer;

public class UserClient {
	private UserInterface userInterface;
	
	public UserClient(String serverIp, String userHash)
			throws MalformedURLException, NotBoundException, RemoteException {
		
		System.setSecurityManager(new RMISecurityManager());
		this.userInterface = (UserInterface) Naming.lookup(serverIp
				+ UserServer.URL_LOCATION);
	}
}
