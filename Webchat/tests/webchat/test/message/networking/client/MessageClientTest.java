package webchat.test.message.networking.client;

import java.io.File;
import java.rmi.RemoteException;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.jmock.Mockery;

import webchat.database.UserDatabase;
import webchat.message.Message;
import webchat.message.MessageComparator;
import webchat.message.networking.NotLoggedInException;
import webchat.message.networking.client.MessageClient;
import webchat.message.networking.server.MessageServer;
import webchat.message.networking.server.MessageService;
import webchat.ui.ClientUI;
import junit.framework.TestCase;

public class MessageClientTest extends TestCase {
	Mockery context = new Mockery();
	
	ClientUI ui;
	private MessageClient client;
	private MessageService service;
	private MessageServer server;
	private UserDatabase database;
	private TreeSet<Message> messages;
	private SortedSet<Message> newMessages;
	private byte[] userInstance = { 0, 0, 0 };

	private boolean notSetUp = true;
	File file;

	@Override
	protected void setUp() throws RemoteException, Exception {
		if (notSetUp) {
			context.setImposteriser(ClassImposteriser.INSTANCE);
			ui = context.mock(ClientUI.class);
			file = new File("testFile.ser");
			file.deleteOnExit();
			new UserDatabase().saveDatabase(file);
		}
		this.database = new UserDatabase(file);
		this.server = new MessageServer(database);
		this.client = new MessageClient("",userInstance, ui);
		notSetUp = true;
		messages = new TreeSet<Message>(new MessageComparator());
	}

	protected void tearDown() {
		this.server = null;
		this.database = null;
		this.newMessages = null;
	}

	public void testSendMessage() {
		try {
			client.sendMessage("test");;
		} catch (RemoteException | NotLoggedInException e) {
			fail();
		}


	}

	
	}


