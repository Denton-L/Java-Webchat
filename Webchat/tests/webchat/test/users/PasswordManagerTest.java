package webchat.test.users;

import junit.framework.TestCase;
import webchat.users.PasswordManager;

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
	
	@Override
	protected void setUp() throws Exception {
		for (int i = 0; i < this.password.length; i++) {
			this.password[i] = 1;
		}
		this.testbool = true;
	}
	
	public void testClearArray() {
		PasswordManager.clearArray(this.testArray);
		for (int i = 0; i < this.cleared.length; i++) {
			if (this.cleared[i] != this.testArray[i]) {
				fail();
			}
		}
	}
	
	public void testServerHashing() throws Exception {
		final byte[] result1 = PasswordManager.serverHash(this.password,
				this.username);
		setUp();
		final byte[] result2 = PasswordManager.serverHash(this.password,
				this.username);
		final byte[] result3 = PasswordManager.serverHash(this.cleared,
				this.username);
		for (int i = 0; i < result1.length; i++) {
			if (result1[i] != result2[i]) {
				fail(result1[i] + " " + result2[i] + " " + i);
			} else if (result1[i] != result3[i]) {
				this.testbool = false;
			}
		}
		if (this.testbool) {
			fail("Hash wasn't properly generated");
		}
		
	}
	
	public void testClientHashing() throws Exception {
		final byte[] result1 = PasswordManager.clientHash(this.password,
				this.username);
		setUp();
		final byte[] result2 = PasswordManager.clientHash(this.password,
				this.username);
		final byte[] result3 = PasswordManager.clientHash(this.cleared,
				this.username);
		for (int i = 0; i < result1.length; i++) {
			if (result1[i] != result2[i]
					|| result1[i] != this.expectedClientHash[i]) {
				fail(result1[i] + " " + result2[i] + " "
						+ this.expectedClientHash[i] + " " + i);
			} else if (result1[i] != result3[i]) {
				this.testbool = false;
			}
		}
		if (this.testbool) {
			fail("Hash wasn't properly generated");
		}
		
	}
	
}
