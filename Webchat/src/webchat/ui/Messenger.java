package webchat.ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import webchat.message.Message;

public class Messenger {
	
	private final MsgScene msgScene;
	
	public Messenger(MsgScene rscene) {
		this.msgScene = rscene;
	}
	
	public void writeMsg(Message message) {
		
		final Text blank = new Text();
		final Text user = new Text(message.getUsername() + " said");
		final Text msg = new Text("\"" + message.getContent() + "\"");
		msg.setWrappingWidth(527);
		final Text time = new Text("" + message.getTimestamp());
		time.setFill(Color.GREY);
		user.setFill(Color.GREY);
		time.setId("textstyle3");
		user.setId("textstyle3");
		
		msg.setId("messagetext");
		
		this.msgScene.getMsgs().getChildren().addAll(user, msg, time, blank);
	}
	
}
