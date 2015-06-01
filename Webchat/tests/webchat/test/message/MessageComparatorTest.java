package webchat.test.message;

import junit.framework.TestCase;
import webchat.message.Message;
import webchat.message.MessageComparator;

public class MessageComparatorTest extends TestCase {
	Message m1 = new Message("test", "test", 100);
	Message m2 = new Message("test", "test", 200);
	MessageComparator messageCompare = new MessageComparator();
	
	public void testCompare() {
		assertTrue(this.messageCompare.compare(this.m1, this.m2) < 0);
		assertTrue(this.messageCompare.compare(this.m2, this.m1) > 0);
		assertTrue(this.messageCompare.compare(this.m1, this.m1) == 0);
	}
	
}
