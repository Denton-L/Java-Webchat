package users.networking.server;

import java.io.Serializable;

import users.networking.UserInterface;

public class UserService implements UserInterface, Serializable {
	
	@Override
	public boolean register(String username, byte[] password) {
		// TODO interface with database
		// remember to hash the password once before sending it off!
		return false;
	}
	
	@Override
	public String signIn(String username, byte[] password) {
		// TODO Auto-generated method stub
		// remember to hash the password once before sending it off!
		//make the modhash a securerandom string
		return null;
	}

	@Override
	public void logout(String username, byte[] password) {
		// TODO Auto-generated method stub
		//remove the user's instancehash
		
	}

	@Override
	public void logout(String instanceHash) {
		// TODO Auto-generated method stub
		
	}
	
}
