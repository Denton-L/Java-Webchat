package networking;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;

import message.networking.server.MessageServer;

public class GenericClient {
	
	protected Remote remoteInterface;
	
	public GenericClient(String serverIp, String sublocation)
			throws MalformedURLException, RemoteException, NotBoundException {
		System.setSecurityManager(new RMISecurityManager());
		remoteInterface = (Remote) Naming.lookup(serverIp + "/" + sublocation);
	}
}
