package webchat.ui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserUpdate {
	MsgScene msgScene;

	public UserUpdate(MsgScene rscene) {
		msgScene = rscene;
	}

	public void writeUser(String user1) 
	{
		Text user = new Text(user1);
		user.setFill(Color.WHITE);
		user.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.8) , 5, 0.0 , 0 , 1);"
				+ "-fx-font-family: AvenirLTStd-Light;"
				+ "-fx-font-size: 30;");
		user.setId(user1);
		msgScene.box2.getChildren().add(user);
	}

	public void userStatus(String user1, boolean status) 
	{
		Node nodee = msgScene.box2.lookup("#" + user1);
		nodee.setId("usernameoff");
		nodee.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0) , 5, 0.0 , 0 , 1);" +
				"-fx-font-family: AvenirLTStd-Light;"
				+ "-fx-font-size:30;");
	}
}
