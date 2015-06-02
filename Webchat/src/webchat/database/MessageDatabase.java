package webchat.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

import webchat.message.Message;
import webchat.message.MessageComparator;

public class MessageDatabase {
	
	// Collection of Message object of user
	private SortedSet<Message> message;
	// The maximum number of messages held
	public static final int MESSAGE_BUFFER = 0xFFF;
	
	// Create a a new empty database
	public MessageDatabase() {
		message = new TreeSet<>(new MessageComparator());
	}
	
	// Create new code from serialized Collection<Message> from code
	public MessageDatabase(File file) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(file));
		this.message = (SortedSet<Message>) objectInputStream.readObject();
		objectInputStream.close();
		
	}
	
	// Saved database
	public void saveDatabase(File file) throws FileNotFoundException,
			IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(file));
		objectOutputStream.writeObject(this.message);
		objectOutputStream.close();
	}
	
	// Adds Message
	public void addMessage(String content, String username) {
		while (message.add(new Message(content, username, System
				.currentTimeMillis())))
			;
		while (message.size() > MESSAGE_BUFFER) {
			message.remove(message.first());
		}
	}
}