package webchat.test.users;

import junit.framework.TestCase;
import webchat.users.User;

public class UserTest extends TestCase {
	User user;
	String username = "test";
	byte[] passwordHash = { 1, 1, 1, 1, 1 };
	byte[] userInstance = { 2, 2, 2, 2, 2 };
	
	@Override
	protected void setUp() throws Exception {
		this.user = new User(this.username, this.passwordHash);
	}
	
	public void testGetMethods() {
		assertSame(this.username, this.user.getUsername());
		assertEquals(this.passwordHash, this.user.getPasswordHash());
	}
	
	public void testUserInstance() {
		this.user.setUserInstance(this.userInstance);
		assertEquals(this.userInstance, this.user.getUserInstance());
	}
	
}
