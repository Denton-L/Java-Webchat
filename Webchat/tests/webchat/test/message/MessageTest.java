package webchat.test.message;

import junit.framework.TestCase;
import webchat.message.Message;

public class MessageTest extends TestCase {
	Message message;
	String content = "test1";
	String user = "test2";
	long timestamp = 100;
	
	@Override
	protected void setUp() throws Exception {
		this.message = new Message(this.content, this.user, this.timestamp);
	}
	
	public void testGetMethods() {
		assertSame(this.content, this.message.getContent());
		assertSame(this.user, this.message.getUsername());
		assertEquals(this.timestamp, this.message.getTimestamp());
	}
	
}
