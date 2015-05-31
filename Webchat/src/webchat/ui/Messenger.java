package webchat.ui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import webchat.message.Message;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Messenger {

	String user;
	String msg;
	String time;

	String myUser;

	MsgScene msgScene;

	public Messenger(MsgScene rscene) {
		msgScene = rscene;
	}

	public void getUser(String username) {
		myUser = username;
	}

	public void writeMsg(Message message) {



		Text blank = new Text();
		Text user = new Text(message.getUsername() + " said");
		Text msg = new Text("\"" + message.getContent() + "\"");
		msg.setWrappingWidth(527);
		Text time = new Text(""+message.getTimestamp());
		time.setFill(Color.GREY);
		user.setFill(Color.GREY);
		time.setId("textstyle3");
		user.setId("textstyle3");

		msg.setId("messagetext");

		msgScene.msgs.getChildren().addAll(user, msg, time, blank);
	}

}
