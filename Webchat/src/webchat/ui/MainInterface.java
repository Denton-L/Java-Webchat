package webchat.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainInterface extends Application {
	String user;
	String newUser;
	String newPass;
	ChatScene chatScene = new ChatScene();
	RegScene regScene = new RegScene();
	MsgScene msgScene = new MsgScene();

	static Font f = Font.loadFont(
			MainInterface.class.getResource("/res/ufonts.com_segoe-webchat.ui-light.ttf")
					.toExternalForm(), 17);
	static Font fLarge = Font.loadFont(
			MainInterface.class.getResource("/res/ufonts.com_segoe-webchat.ui-light.ttf")
					.toExternalForm(), 25);
	static Font fBig = Font.loadFont(
			MainInterface.class.getResource("/res/ufonts.com_segoe-webchat.ui-light.ttf")
					.toExternalForm(), 45);
	UserUpdate userupd = new UserUpdate(msgScene);
	Messenger messenger = new Messenger(msgScene);

	public static void main(String[] args) {
		System.out.println("asdf");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Font.loadFont(MainInterface.class.getResource("/res/NexaLight.otf")
				.toExternalForm(), 17);

		Scene chat = chatScene.createChat();
		Scene reg = regScene.createReg();
		Scene msg = msgScene.createMsg();

		primaryStage.setTitle("Chat");
		primaryStage.setScene(chat);
		primaryStage.show();

		userupd.writeUser("lucychoi");
		userupd.writeUser("cindypiao");
		messenger
				.writeMsg(
						"sample user",
						"lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam mauris neque, imperdiet ac porta ut, feugiat a quam. Praesent laoreet sapien sem, et sodales arcu vulputate eget.",
						"sample time");
		userupd.userStatus("cindypiao", true);

		chatScene.register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				chatScene.clrAll();
				primaryStage.setScene(reg);
			}
		});

		chatScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UserLogin userlogin = new UserLogin(chatScene.userBox.getText(),
						chatScene.pwBox.getText(), chatScene.ipBox.getText());
				if (userlogin.infoCheck() == true) {
					messenger.getUser(chatScene.userBox.getText());
					System.out.println(chatScene.userBox.getText());
					primaryStage.setScene(msg);
					chatScene.clrAll();
					chatScene.warning.setVisible(false);
				} else
					chatScene.warning.setVisible(true);
			}
		});

		regScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UserInfo userinfo = new UserInfo(regScene.userBox.getText(),
						regScene.pwBox.getText(), regScene.pwBox2.getText());
				if (userinfo.userpasscheck() == true) {
					primaryStage.setScene(chat);
					regScene.clrAll();
					// call userinfo to get new user info
				} else
					regScene.warning.setVisible(true);
			}
		});

		msgScene.logout.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				primaryStage.setScene(chat);
				msgScene.clrAll();
			}

		});

		msgScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				messenger.deliverMsg();
			}
		});

		msgScene.input.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent ke) {
						if (ke.getCode().equals(KeyCode.ENTER)) {
							messenger.deliverMsg();
							// call messenger for message;
							ke.consume();
						}
					}
				});

	}

	public static Font getFont() {
		return f;
	}

	public static Font getFont2() {
		return fLarge;
	}

	public static Font getFont3() {
		return fBig;
	}
}