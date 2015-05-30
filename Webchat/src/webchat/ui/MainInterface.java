package webchat.ui;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import webchat.users.networking.client.UserClient;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main Interface object
 * 
 * Takes care of interaction between GUI objects and event handling.
 * 
 * @author Jing Yi Xie
 * @version 1.0
 */

public class MainInterface extends Application {
	String user;
	String newUser;
	String newPass;
	final ServScene servScene = new ServScene();
	final ChatScene chatScene = new ChatScene();
	final RegScene regScene = new RegScene();
	final MsgScene msgScene = new MsgScene();

	static Font f = Font.loadFont(
			MainInterface.class.getResource("/res/SegoeUILight.ttf")
					.toExternalForm(), 17);
	static Font fLarge = Font.loadFont(
			MainInterface.class.getResource("/res/SegoeUILight.ttf")
					.toExternalForm(), 25);
	static Font fBig = Font.loadFont(
			MainInterface.class.getResource("/res/SegoeUILight.ttf")
					.toExternalForm(), 45);
	static Font font = Font.loadFont(
			MainInterface.class.getResource("/res/AvenirLTStd-Light.otf")
					.toExternalForm(), 17);
	static Font font2 = Font.loadFont(
			MainInterface.class.getResource("/res/NexaLight.otf")
					.toExternalForm(), 17);

	UserUpdate userupd = new UserUpdate(msgScene);
	Messenger messenger = new Messenger(msgScene);
	UserInfo userinfo = new UserInfo();
	ServerAddr servaddr = new ServerAddr(servScene);

	UserClient client;
	byte[] userInstance;

	/**
	 * Main method used to run the applications.
	 * 
	 * @param Unused
	 *            .
	 * @return Nothing.
	 */

	public static void main(String[] args) {
		System.out.println(font.getName());
		launch(args);
	}

	/**
	 * This is used to set up the application by adding event handlers to the
	 * appropriate elements and changing the scene when necessary.
	 */
	public void start(final Stage primaryStage) {

		final Scene serv = servScene.createServ();
		final Scene chat = chatScene.createChat();
		final Scene reg = regScene.createReg();
		final Scene msg = msgScene.createMsg();

		
		
		primaryStage.setTitle("Web Chat");
		primaryStage.setScene(serv);
		primaryStage.setResizable(false);
		primaryStage.setWidth(serv.getWidth());
		primaryStage.setHeight(serv.getHeight());
		primaryStage.show();

		messenger.writeMsg("hello", "hello", "hello");
		userupd.writeUser("lucychoi");
		userupd.writeUser("cindypiao");
		userupd.userStatus("cindypiao", true);

		primaryStage.heightProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldvalue,
					Object newValue) {
				msgScene.imview.setFitHeight(primaryStage.getHeight());
				msgScene.imview.setFitWidth(primaryStage.getWidth());
				regScene.imview.setFitHeight(primaryStage.getHeight());
				regScene.imview.setFitWidth(primaryStage.getWidth());
				chatScene.imview.setFitHeight(primaryStage.getHeight());
				chatScene.imview.setFitWidth(primaryStage.getWidth());
			}
		});

		chatScene.register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				chatScene.clrAll();
				primaryStage.setScene(reg);
			}
		});

		servScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					client = new UserClient(servScene.ipBox.getText());
					servaddr.getAddress();
					servScene.clrAll();
					primaryStage.setScene(chat);
				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					servScene.warning.setVisible(true);
				}
			}
		});

		chatScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					userInstance = client.signIn(chatScene.userBox.getText(),
							chatScene.pwBox.getText().getBytes());
					if (userInstance != null) {
						primaryStage.setScene(msg);
						chatScene.clrAll();
					} else
						chatScene.warning.setVisible(true);
				} catch (RemoteException e) {
					chatScene.warning.setVisible(true);
				}
			}
		});

		regScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (regScene.pwBox.getText().equals(regScene.pwBox2.getText())) {
					try {
						if (client.register(regScene.userBox.getText(),
								regScene.pwBox.getText().getBytes())) {
							primaryStage.setScene(chat);
							regScene.clrAll();
						} else
							regScene.warning.setVisible(true);
					} catch (RemoteException e) {
						regScene.warning.setVisible(true);
					}
				}
			}
		});

		msgScene.logout.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				primaryStage.setScene(serv);
				msgScene.clrAll();
			}

		});

		msgScene.enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (msgScene.input.getText() != null
						&& !msgScene.input.getText().trim().isEmpty())
					messenger.deliverMsg();
			}
		});

		msgScene.input.addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent ke) {
						if (ke.getCode().equals(KeyCode.ENTER)) {
							if (msgScene.input.getText() != null
									&& !msgScene.input.getText().trim()
											.isEmpty())
								messenger.deliverMsg();
							// call messenger for message;
							ke.consume();
						}
					}
				});
	}
}