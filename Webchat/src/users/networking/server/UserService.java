package users.networking.server;

import java.io.Serializable;
import java.security.SecureRandom;

import users.PasswordManager;
import users.User;
import users.networking.UserInterface;

public class UserService implements UserInterface, Serializable {
	
	private final static int USER_INSTANCE_LENGTH = 0xFF;
	
	private User userFromNameAndPassword(String username, byte[] password) {
		return new User(username, PasswordManager.serverHash(password, username));
	}
	
	@Override
	public boolean register(String username, byte[] password) {
		User user = userFromNameAndPassword(username, password);
		if (true /*doesn't exist in database*/) {
			//add to database
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public byte[] signIn(String username, byte[] password) {
		User user = userFromNameAndPassword(username, password);
		byte[] userInstance = new byte[USER_INSTANCE_LENGTH];
		// get user from database
		new SecureRandom().nextBytes(userInstance);
		//store userInstance in database
		return userInstance;
	}

	@Override
	public void logout(byte[] userInstance) {
		//remove userInstance from database
		
	}
	
}
