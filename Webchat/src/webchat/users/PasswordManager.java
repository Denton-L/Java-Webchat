package webchat.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class contains many static methods to handle passwords. The hash used
 * will be SHA-256.
 *
 * @author Denton Liu
 * @version 2015-05-25
 */
public class PasswordManager {
	
	/** The hash function used by this class. */
	private static final String HASH_FUNCTION = "SHA-256";
	/** The number of hashes done by the server. */
	private static final int NUMBER_OF_SERVER_HASHES = 1000;
	
	/**
	 * Fills an array that is passed to it with zeros.
	 *
	 * @param cleared
	 *            The {@code byte[]} to be cleared.
	 */
	public static void clearArray(byte[] cleared) {
		for (int i = 0; i < cleared.length; i++) {
			cleared[i] = 0;
		}
	}
	
	/**
	 * Hashes a password salted with the username. Note that the password array
	 * will be cleared after it has been passed.
	 *
	 * @param password
	 * @param username
	 * @return A {@code byte[]} containing the hashed data.
	 */
	public static byte[] clientHash(byte[] password, String username) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
			
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		messageDigest.update(password);
		messageDigest.update(username.getBytes());
		
		clearArray(password);
		
		return messageDigest.digest();
	}
	
	/**
	 * Hashes a password salted with the username many times. Note that the
	 * password array will be cleared after it has been passed.
	 *
	 * @param password
	 * @param username
	 * @return A {@code byte[]} containing the hashed data.
	 */
	public static byte[] serverHash(byte[] password, String username) {
		MessageDigest messageDigest;
		byte[] hash;
		
		try {
			messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		messageDigest.update(password);
		messageDigest.update(username.getBytes());
		hash = messageDigest.digest();
		clearArray(password);
		
		for (int hashNumber = 0; hashNumber < NUMBER_OF_SERVER_HASHES; hashNumber++) {
			messageDigest.update(hash);
			messageDigest.update(username.getBytes());
			clearArray(hash);
			hash = messageDigest.digest();
		}
		
		return hash;
	}
}
