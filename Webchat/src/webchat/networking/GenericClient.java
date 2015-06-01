package webchat.networking;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * A generic RMI client from which other clients will inherit.
 * 
 * @author Denton Liu
 * @author Filip Francetic
 * @version 2015-05-25
 */
public abstract class GenericClient {
	
	/** The {@code Remote} class which this client will accept. */
	private Remote remoteInterface;
	
	/**
	 * 
	 * @param serverURL
	 *            The location of the server.
	 * @param sublocation
	 *            The location of the class on the server.
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public GenericClient(String serverURL, String sublocation)
			throws MalformedURLException, RemoteException, NotBoundException {
		System.setSecurityManager(new RMISecurityManager());
		
		Registry registry = LocateRegistry.getRegistry(serverURL);
		remoteInterface = (Remote) registry.lookup(sublocation);
	}
	
	protected Remote getRemoteInterface() {
		return remoteInterface;
	}
}
