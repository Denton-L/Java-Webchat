package networking;
import java.rmi.Remote;

public interface MessageInterface extends Remote{
	public void push (Message message);
	public Message pull (Message message);
}
