package webchat.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import webchat.users.User;

public class UserDatabase implements Serializable {
	
	private Set<User> users;
	
	public UserDatabase() {
		users = new TreeSet<>();
	}
	
	public UserDatabase(File file) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(file))) {
			this.users = (Set<User>) objectInputStream.readObject();
		}
	}
	
	public boolean addUser(String username, byte[] passwordHash) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			if (iterator.next().getUsername().equals(username)) {
				return false;
			}
		}
		users.add(new User(username, passwordHash));
		return true;
	}
	
	public boolean checkUserAndPassword(String username, byte[] password) {
		
	}
	
}
