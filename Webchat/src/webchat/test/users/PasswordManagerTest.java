package webchat.test.users;

import webchat.users.PasswordManager;
import junit.framework.TestCase;
import junit.framework.Assert;

public class PasswordManagerTest extends TestCase {
	byte[] password = { 1, 1, 1, 1, 1 };
	byte[] passwordReset = { 1, 1, 1, 1, 1 };
	byte[] testArray = { 2, 5, 7, 3, 1 };
	byte[] cleared = { 0, 0, 0, 0, 0 };
	byte[] expectedClientHash = { -53, -47, -72, -88, 94, -2, -49, 75, 0, -124,
			0, 81, -22, 34, -77, -113, 48, -100, 94, -126, -56, 115, 89, -54,
			122, 80, -101, -116, -4, -60, -4, 67 };
	String username = "test";
	boolean testbool;

	protected void setUp() throws Exception {
		for (int i = 0; i < password.length; i++)
			password[i] = 1;
		testbool = true;
	}

	public void testClearArray() {
		PasswordManager.clearArray(testArray);
		for (int i = 0; i < cleared.length; i++)
			if (cleared[i] != testArray[i])
				fail();
	}

	public void testServerHashing() throws Exception {
		byte[] result1 = PasswordManager.serverHash(password, username);
		setUp();
		byte[] result2 = PasswordManager.serverHash(password, username);
		byte[] result3 = PasswordManager.serverHash(cleared, username);
		for (int i = 0; i < result1.length; i++){
			if (result1[i] != result2[i])
				fail(result1[i] + " " + result2[i] + " " + i);
			else if (result1[i] != result3[i])
				testbool = false;
		}
		if (testbool)
			fail("Hash wasn't properly generated");
		
	}

	public void testClientHashing() throws Exception {
		byte[] result1 = PasswordManager.clientHash(password, username);
		setUp();
		byte[] result2 = PasswordManager.clientHash(password, username);
		byte[] result3 = PasswordManager.clientHash(cleared, username);
		for (int i = 0; i < result1.length; i++){
			if (result1[i] != result2[i] || result1[i] != expectedClientHash[i])
				fail(result1[i] + " " + result2[i] + " "
						+ expectedClientHash[i] + " " + i);
			else if (result1[i] != result3[i])
				testbool = false;
		}
		if (testbool)
			fail("Hash wasn't properly generated");
		
	}

}
