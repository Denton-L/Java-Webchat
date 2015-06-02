package webchat.test.message.networking.server;

import java.io.File;
import java.rmi.RemoteException;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;
import webchat.database.UserDatabase;
import webchat.message.Message;
import webchat.message.MessageComparator;
import webchat.message.networking.NotLoggedInException;
import webchat.message.networking.server.MessageService;

public class MessageServiceTest extends TestCase {
	private MessageService service;
	private UserDatabase database;
	private TreeSet<Message> messages;
	private SortedSet<Message> newMessages;
	private byte[] userInstance = { 0, 0, 0 };

	private boolean notSetUp = true;
	File file;

	@Override
	protected void setUp() throws RemoteException, Exception {
		if (notSetUp) {
			file = new File("testFile.ser");
			file.deleteOnExit();
			new UserDatabase().saveDatabase(file);
		}
		this.database = new UserDatabase(file);
		this.service = new MessageService(database);
		notSetUp = true;
		messages = new TreeSet<Message>(new MessageComparator());
	}

	protected void tearDown() {
		this.service = null;
		this.database = null;
		this.newMessages = null;
	}

	public void testPullEmpty() {
		try {
			newMessages = service.pull(null);
		} catch (RemoteException e) {
			fail();
		}

		for (Message message : newMessages) {
			assertTrue(messages.contains(message));
		}

	}

	public void testPullNotEmptyAndPush() {
		database.addUser("test", new byte[] { 0, 0, 0 });
		database.setUserInstance("test", userInstance);
		try {
			service.push("testmessage1", userInstance);
			service.push("testmessage2", userInstance);
			newMessages = service.pull(null);
		} catch (RemoteException | NotLoggedInException e) {
			fail();
		}
		messages.add(new Message("testmessage1", "test", 0));
		messages.add(new Message("testmessage2", "test", 5));
		for (int i = 0; i < newMessages.toArray().length; i++) {
			assertSame(((Message) newMessages.toArray()[i]).getContent(),
					((Message) messages.toArray()[i]).getContent());
			assertSame(((Message) newMessages.toArray()[i]).getUsername(),
					((Message) messages.toArray()[i]).getUsername());
		}

	}

}
