package webchat.test.message;

import webchat.message.Message;
import webchat.message.MessageComparator;
import junit.framework.TestCase;
import junit.framework.Assert;

public class MessageComparatorTest extends TestCase {
	Message m1 = new Message("test", "test", 100);
	Message m2 = new Message("test", "test", 200);
	MessageComparator messageCompare = new MessageComparator();
	
	public void testCompare() {
		assertTrue(messageCompare.compare(m1, m2) < 0);
		assertTrue(messageCompare.compare(m2, m1) > 0);
		assertTrue(messageCompare.compare(m1, m1) == 0);
	}
	
}
