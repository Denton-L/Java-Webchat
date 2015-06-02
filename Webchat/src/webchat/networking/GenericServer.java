package webchat.networking;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

/**
 * A generic RMI server from which other clients will inherit.
 *
 * @author Denton Liu
 * @author Filip Francetic
 * @version 2015-05-25
 */
public abstract class GenericServer {
	
	/** The location of the class on the server. */
	private final String location;
	/** The {@code Remote} that will be bound to this server. */
	private final Remote binding;
	
	private static boolean registryNotStarted = true;
	
	Registry registry;
	
	/**
	 * @param location
	 *            The location of the class on the domain.
	 * @param binding
	 *            The {@code Remote} that will be bound to this server.
	 */
	public GenericServer(String location, Remote binding) {
		this.location = location;
		this.binding = binding;
	}
	
	/**
	 * Starts the server and binds the {@code Remote} object to the specified
	 * location.
	 *
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	public void startServer() throws MalformedURLException, RemoteException,
			AlreadyBoundException {
		
		System.setSecurityManager(new RMISecurityManager());
		try {
			if (registryNotStarted) {
				LocateRegistry.createRegistry(1099);
				registryNotStarted = false;
			}
		} catch (final ExportException e) {
			registryNotStarted = false;
		}
		registry = LocateRegistry.getRegistry();
		registry.rebind(this.location, this.binding);
	}
	
	public void shutDown() throws AccessException, RemoteException, NotBoundException {
		registry.unbind(this.location);
		UnicastRemoteObject.unexportObject(binding,true);
	}
}
