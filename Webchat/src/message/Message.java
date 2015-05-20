package message;

import java.io.Serializable;

public class Message implements Serializable {
	private String content;
	private String username;
	private long timestamp;
	
	public Message(String content, String username, long timestamp) {
		this.content = content;
		this.username = username;
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public String getUsername() {
		return username;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
}
