package webchat.ui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

	public void writeMsg(String user1, String msg1, String time1) {
		user = user1;
		msg = msg1;
		time = time1;

		if (user1.equals("you")) {
			System.out.println(myUser);
			user = myUser;
		}

		Text blank = new Text();
		Text user = new Text(user1 + " said");
		Text msg = new Text("\"" + msg1 + "\"");
		msg.setWrappingWidth(527);
		Text time = new Text(time1);
		time.setFill(Color.GREY);
		user.setFill(Color.GREY);
		time.setId("textstyle3");
		user.setId("textstyle3");

		msg.setId("messagetext");

		msgScene.msgs.getChildren().addAll(user, msg, time, blank);
	}

	public String deliverMsg() {
		String msg = msgScene.input.getText();
		msgScene.input.clear();
		String time;

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		time = new SimpleDateFormat("HH:mm").format(timestamp);

		writeMsg("you", msg, time);
		
		return msg;
	}
}
