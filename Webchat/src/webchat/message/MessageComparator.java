package webchat.message;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A comparator which sorts {@code Message}s in ascending order by their
 * {@code timestamp}.
 * 
 * @author Denton Liu
 * @version 2015-05-23
 */
public class MessageComparator implements Comparator<Message>, Serializable {
	
	/**
	 * Note: this comparator imposes orderings that are inconsistent with
	 * equals.
	 */
	@Override
	public int compare(Message m1, Message m2) {
		return (int) (m1.getTimestamp() - m2.getTimestamp());
	}
}
