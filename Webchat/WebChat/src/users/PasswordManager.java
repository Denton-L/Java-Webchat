package users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
	
	public static final int NUMBER_OF_HASHES = 1000;
	
	private static void clearArray(byte[] cleared) {
		for (int i = 0; i < cleared.length; i++) {
			cleared[i] = 0;
		}
	}
	
	public static byte[] clientHash(byte[] password, String username) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		messageDigest.update(password);
		messageDigest.update(username.getBytes());
		
		clearArray(password);
		
		return messageDigest.digest();
	}
	
	public static byte[] serverHash(byte[] password, String username) {
		MessageDigest messageDigest;
		byte[] hash;
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		messageDigest.update(password);
		messageDigest.update(username.getBytes());
		hash = messageDigest.digest();
		
		for (int hashNumber = 0; hashNumber < NUMBER_OF_HASHES; hashNumber++) {
			hash = messageDigest.digest(hash);
		}
		
		return hash;
	}
}
