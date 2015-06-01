package webchat.test.message;

import webchat.message.Message;
import junit.framework.TestCase;
import junit.framework.Assert;

public class MessageTest extends TestCase {
	Message message;
	String content = "test1";
	String user = "test2";
	long timestamp = 100;
	
	protected void setUp() throws Exception {
		message = new Message(content, user, timestamp);
	}
	
	public void testGetMethods() {
		assertSame(content, message.getContent());
		assertSame(user, message.getUsername());
		assertEquals(timestamp, message.getTimestamp());
	}
	
}
