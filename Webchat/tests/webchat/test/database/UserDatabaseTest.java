package webchat.test.database;

import junit.framework.TestCase;
import webchat.database.UserDatabase;

public class UserDatabaseTest extends TestCase {
	
	// public void testUserDatabase() {
	// fail("Not yet implemented");
	// }
	//
	// public void testUserDatabaseFile() {
	// fail("Not yet implemented");
	// }
	//
	// public void testSaveDatabase() {
	// fail("Not yet implemented");
	// }
	
	public void testAddUser() {
		UserDatabase userDatabase = new UserDatabase();
		assertTrue(userDatabase.addUser("asdf", "password".getBytes()));
		assertFalse(userDatabase.addUser("asdf", "password".getBytes()));
		assertFalse(userDatabase.addUser("asdf", "anything".getBytes()));
		assertTrue(userDatabase.addUser("asdf2", "passewoasdd".getBytes()));
	}
	
	public void testIsCorrectUserAndPassword() {
		UserDatabase userDatabase = new UserDatabase();
		userDatabase.addUser("asdf", "password".getBytes());
		userDatabase.addUser("fdsa", "random".getBytes());
		assertTrue(userDatabase.isCorrectUserAndPassword("asdf",
				"password".getBytes()));
		assertFalse(userDatabase.isCorrectUserAndPassword("asdff",
				"password".getBytes()));
		assertFalse(userDatabase.isCorrectUserAndPassword("asdf",
				"passsword".getBytes()));
		assertTrue(userDatabase.isCorrectUserAndPassword("fdsa",
				"random".getBytes()));
		assertFalse(userDatabase.isCorrectUserAndPassword("fdsaa",
				"random".getBytes()));
		assertFalse(userDatabase.isCorrectUserAndPassword("fdsa",
				"randomm".getBytes()));
	}
	
	public void testSetUserInstance() {
		testGetUsernameFromUserInstance();
	}
	
	public void testGetUsernameFromUserInstance() {
		UserDatabase userDatabase = new UserDatabase();
		userDatabase.addUser("asdf", "password".getBytes());
		userDatabase.setUserInstance("asdf", new byte[] { 1, 2, 3, 4 });
		assertEquals(
				"asdf",
				userDatabase.getUsernameFromUserInstance(new byte[] { 1, 2, 3,
						4 }));
		assertNull(userDatabase.getUsernameFromUserInstance(new byte[] { 1, 1,
				1 }));
	}
	
}
