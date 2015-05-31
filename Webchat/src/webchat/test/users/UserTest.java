package webchat.test.users;

import webchat.users.User;
import junit.framework.TestCase;
import junit.framework.Assert;

public class UserTest extends TestCase {
	User user;
	String username = "test";
	byte[] passwordHash = {1,1,1,1,1};
	byte[] userInstance = {2,2,2,2,2};

	protected void setUp() throws Exception {
		user = new User(username,passwordHash);
	}

	public void testGetMethods(){
		assertSame(username, user.getUsername());
		assertEquals(passwordHash, user.getPasswordHash());
	}
	
	public void testUserInstance(){
		user.setUserInstance(userInstance);
		assertEquals(userInstance,user.getUserInstance());
	}
	

}
