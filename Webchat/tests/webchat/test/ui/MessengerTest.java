package webchat.test.ui;

import java.util.SortedSet;
import java.util.TreeSet;

import javafx.scene.text.Text;
import junit.framework.TestCase;
import webchat.message.Message;
import webchat.ui.ClientUI;
import webchat.ui.MsgScene;

public class MessengerTest extends TestCase
{
	Message message = new Message("hello", "hello2", 123);
	SortedSet<Message> messages = new TreeSet<Message>();
	
	ClientUI clientui = new ClientUI();
	MsgScene msgscene = new MsgScene();
		
	Text vessel;
	
	public void setUp() 
	{
		messages.add(message);
		clientui.writeMsg(messages);
		vessel = (Text) msgscene.msgs.getChildren().get(0);
	}

	public void testUser() 
	{
		assertEquals("hello said", vessel.getText());
	}
}
