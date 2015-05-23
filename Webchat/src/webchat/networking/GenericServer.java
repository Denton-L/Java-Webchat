package webchat.networking;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class GenericServer {
	
	private String location;
	private Remote binding;
	
	public GenericServer(String location, Remote binding) {
		this.location = location;
		this.binding = binding;
	}
	
	public void startServer() throws MalformedURLException, RemoteException, AlreadyBoundException {
		System.setSecurityManager(new RMISecurityManager());
		
		Naming.bind("rmi://localhost/" +location, binding);
		
	}
}
