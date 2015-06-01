package webchat.message;

import java.io.Serializable;

/**
 * This class contains a message which will be transmitted between the server
 * and the client.
 *
 * @author Denton Liu
 * @version 2015-05-21
 *
 */
public class Message implements Serializable {
	
	/** Auto-generated. */
	private static final long serialVersionUID = -6462903128109313504L;
	/** A {@code String} containing the message content. */
	private final String content;
	/** A {@code String} containing the username of the message sender. */
	private final String username;
	/** The time of when the server receives this transmission. */
	private final long timestamp;
	
	/**
	 * @param content
	 *            A {@code String} containing the message content.
	 * @param username
	 *            A {@code String} containing the username of the message
	 *            sender.
	 * @param timestamp
	 *            The time when the server receives this transmission.
	 */
	public Message(String content, String username, long timestamp) {
		this.content = content;
		this.username = username;
		this.timestamp = timestamp;
	}
	
	/**
	 *
	 * @return A {@code String} containing the message content.
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 *
	 * @return A {@code String} containing the username of the message sender.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 *
	 * @return The time when the server receives this transmission.
	 */
	public long getTimestamp() {
		return this.timestamp;
	}
	
}
