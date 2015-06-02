package webchat.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import webchat.message.Message;
import webchat.users.User;

public class MessageDatabase {
	
	// Collection of Message object of user
	private Collection<Message> message;
	
	// Create a a new empty database
	public MessageDatabase() {
		message = new ArrayList<>();
	}
	
	// Create new code from serialized Collection<Message> from code
	public MessageDatabase(File file) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(file));
		this.message = (Collection<Message>) objectInputStream.readObject();
		objectInputStream.close();
		
		// clears MessageInterface
		for (Iterator<Message> message = message.iterator(); message.hasNext();) {
			message.next().setUserInstance(null);
		}
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
	public boolean addMessage(String username, byte[] passwordHash) {
		for (Iterator<User> iterator = message.iterator(); iterator.hasNext();) {
			if (iterator.next().getContent().equals(username)) {
				return false;
			}
		}
		message.add(new Message(username, content));
		return true;
	}
	
	// Get Message from a timestamp
	public String getContentFromTimestamp(long[] timestamp) {
		for (Iterator<User> iterator = message.iterator(); iterator.hasNext();) {
			Message message = iterator.next();
			if (Arrays.equals(message.getContent(), content)) {
				return message.getContent();
			}
		}
		
		return null;
	}
	
	// Purge Messages
	public String purgeMessages() {
		
	}
}