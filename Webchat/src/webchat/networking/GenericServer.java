package webchat.networking;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

/**
 * A generic RMI server from which other clients will inherit.
 * 
 * @author Denton Liu
 * @author Filip Francetic
 * @version 2015-05-25
 */
public abstract class GenericServer {
	
	/** The location of the server. */
	private String location;
	/** The {@code Remote} that will be bound to this server. */
	private Remote binding;
	
	private static boolean registryNotStarted = true;
	/**
	 * 
	 * @param binding
	 *            The {@code Remote} that will be bound to this server.
	 */
	public GenericServer(Remote binding) {
		this.binding = binding;
		this.location = null;
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
		try{
			if (registryNotStarted){
				LocateRegistry.createRegistry(1099);
//				registryNotStarted = false;
			}
		}
		catch (ExportException e){
			registryNotStarted = false;
		}
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind(location, binding);
	}
	
	/**
	 * Sets the sublocation of the server.
	 * 
	 * @param location The sublocation of the server.
	 */
	protected void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Gets the sublocation of the server.
	 * 
	 * @return The sublocation of the server.
	 */
	public String getLocation() {
		return this.location;
	}
}
