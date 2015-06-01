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

	/** Auto-generated. */
	private static final long serialVersionUID = 3978673896611590345L;
	
	/**
	 * Note: this comparator imposes orderings that are inconsistent with
	 * equals.
	 */
	@Override
	public int compare(Message m1, Message m2) {
		if (m1 == null && m2 == null) {
			return 0;
		}
		if (m1 == null) {
			return -1;
		}
		if (m2 == null) {
			return 1;
		}

		return (int) (m1.getTimestamp() - m2.getTimestamp());
	}
}
