package webchat.test.networking;

import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import webchat.database.UserDatabase;
import webchat.networking.StartServer;
import junit.framework.TestCase;

public class StartServerTest extends TestCase {

	public void testStartServer() {
		File file = new File("testFile.ser");
		file.deleteOnExit();
		try {
			new UserDatabase().saveDatabase(file);
			StartServer.start(file);
		} catch (ClassNotFoundException | IOException | AlreadyBoundException e) {
			fail();
			e.printStackTrace();
		}
		try {
			LocateRegistry.createRegistry(1099);
			fail();
		} catch (RemoteException e) {
		}

		try {
			StartServer.stop();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			fail();
		}		

	}
}
