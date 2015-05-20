package message;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {
	
	/**
	 * Note: this comparator imposes orderings that are inconsistent with
	 * equals.
	 */
	@Override
	public int compare(Message m1, Message m2) {
		return (int) (m1.getTimestamp() - m2.getTimestamp());
	}
}
