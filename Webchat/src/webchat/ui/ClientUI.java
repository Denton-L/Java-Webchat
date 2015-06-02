package webchat.ui;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.SortedSet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import webchat.message.Message;
import webchat.message.networking.NotLoggedInException;
import webchat.message.networking.client.MessageClient;
import webchat.users.networking.client.UserClient;

/**
 * Main Interface object
 *
 * Takes care of interaction between GUI objects and event handling.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class ClientUI extends Application implements EventHandler<ActionEvent> {
	private final ServScene servScene = new ServScene();
	private final LoginScene loginScene = new LoginScene();
	private final RegScene regScene = new RegScene();
	private final MsgScene msgScene = new MsgScene();

	private Text blank;
	private Text userName;
	private Text msgText;
	private Text time;
	private Text onlineUser;

	private Scene serv;
	private Scene login;
	private Scene reg;
	private Scene msg;

	private volatile boolean hasNotAdded;
	private volatile boolean hasNotRemoved;
	private UserClient client;
	private MessageClient msgclient;
	private String ip;
	private byte[] userInstance;
	private Stage stage;



	public void writeMsg(SortedSet<Message> messages) {
		for (final Message message : messages) {
			try {
				this.blank = new Text();
				this.userName = new Text(message.getUsername() + " said");
				this.msgText = new Text("\"" + message.getContent() + "\"");
				this.msgText.setWrappingWidth(527);
				this.time = new Text(""
						+ new Date(message.getTimestamp()).toString());
				this.time.setFill(Color.GREY);
				this.userName.setFill(Color.GREY);
				this.time.setId("textstyle3");
				this.userName.setId("textstyle3");

				this.msgText.setId("messagetext");
				this.msgScene
						.getMsgs()
						.getChildren()
						.addAll(this.userName, this.msgText, this.time,
								this.blank);
			} catch (final Exception e) {
				reportMessageException(e);
			}
		}
	}

	public void writeUsers(String[] users) {
		if (ClientUI.this.msgScene.getBox2().getChildren().size() > 0) {
			try {
				hasNotRemoved = true;
				ClientUI.this.msgScene
						.getBox2()
						.getChildren()
						.remove(0,
								ClientUI.this.msgScene.getBox2().getChildren()
										.size());
				hasNotRemoved = false;
			} catch (final Exception e) {
				reportRemoveUserNameException(e);
			}
		} else
			hasNotRemoved = false;

		while (hasNotRemoved) {
		}

		for (final String userName : users) {
			try {
				hasNotAdded = true;
				this.onlineUser = new Text(userName);
				this.onlineUser.setFill(Color.WHITE);
				this.onlineUser
						.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.8) , 5, 0.0 , 0 , 1);"
								+ "-fx-font-family: AvenirLTStd-Light;"
								+ "-fx-font-size: 30;");
				this.onlineUser.setId(userName);
				this.msgScene.getBox2().getChildren().add(this.onlineUser);
				hasNotAdded = false;
			} catch (final Exception e) {
				reportUserNameException(e);
			}
			while (hasNotAdded) {
			}
		}
	}

	public ClientUI getUI() {
		return this;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void reportMessageException(final Throwable t) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ClientUI.this.msgScene
						.getMsgs()
						.getChildren()
						.addAll(ClientUI.this.userName, ClientUI.this.msgText,
								ClientUI.this.time, ClientUI.this.blank);
			}
		});
	}

	private void reportUserNameException(final Throwable t) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ClientUI.this.msgScene.getBox2().getChildren()
						.add(ClientUI.this.onlineUser);
				hasNotAdded = false;
			}
		});
	}

	private void reportRemoveUserNameException(final Throwable t) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ClientUI.this.msgScene
						.getBox2()
						.getChildren()
						.remove(0,
								ClientUI.this.msgScene.getBox2().getChildren()
										.size());
				hasNotRemoved = false;
			}
		});
	}

	/**
	 * This is used to set up the application by adding event handlers to the
	 * appropriate elements and changing the scene when necessary.
	 */
	@Override
	public void start(final Stage primaryStage) {
		Font.loadFont(ClientUI.class.getResource("/AvenirLTStd-Light.otf")
				.toExternalForm(), 10);
		Font.loadFont(ClientUI.class.getResource("/NexaLight.otf")
				.toExternalForm(), 10);

		this.serv = this.servScene.createServ();
		this.login = this.loginScene.createChat();
		this.reg = this.regScene.createReg();
		this.msg = this.msgScene.createMsg();

		final StageModifier stagemod = new StageModifier(this.serv, this.login,
				this.reg, this.msg, this.servScene, this.msgScene, this.loginScene,
				this.regScene, primaryStage);
		stagemod.draggable();
		stagemod.testmethod();

		this.stage = primaryStage;

		primaryStage.setTitle("Web Chat");
		primaryStage.setScene(this.serv);
		primaryStage.setResizable(false);
		primaryStage.setWidth(this.serv.getWidth());
		primaryStage.setHeight(this.serv.getHeight());
		primaryStage.getIcons().add(new Image("/ChatIcon.png" ));
		primaryStage.show();

		this.loginScene.getRegister().setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						ClientUI.this.loginScene.clrAll();
						primaryStage.setScene(ClientUI.this.reg);
					}
				});

		this.loginScene.getEnter().setOnAction(this);

		this.servScene.getEnter().setOnAction(this);

		this.regScene.getEnter().setOnAction(this);

		this.msgScene.getEnter().setOnAction(this);

		this.msgScene.getLogout().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						primaryStage.setScene(ClientUI.this.login);
						ClientUI.this.msgScene.clrAll();
						ClientUI.this.msgclient.stopClient();
						try {
							ClientUI.this.client
									.logout(ClientUI.this.userInstance);
							ClientUI.this.client = null;
							ClientUI.this.msgclient = null;
						} catch (final RemoteException e) {
							e.printStackTrace();
						}
					}

				});

		this.msgScene.getInput().addEventFilter(KeyEvent.KEY_PRESSED,
				new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent ke) {
						if (ke.getCode().equals(KeyCode.ENTER)) {
							if (ClientUI.this.msgScene.getInput().getText() != null
									&& !ClientUI.this.msgScene.getInput()
											.getText().trim().isEmpty()) {
								try {
									ClientUI.this.msgclient
											.sendMessage(ClientUI.this.msgScene
													.getInput().getText());
								} catch (final RemoteException e) {
									e.printStackTrace();
								} catch (final NotLoggedInException e) {
									try {
										ClientUI.this.client
												.logout(ClientUI.this.userInstance);
										ClientUI.this.client = null;
										ClientUI.this.msgclient = null;
										primaryStage
												.setScene(ClientUI.this.login);
										ClientUI.this.msgScene.clrAll();
										ClientUI.this.msgclient.stopClient();
									} catch (final RemoteException e1) {
										e1.printStackTrace();
									}
								}
								ClientUI.this.msgScene.getInput().clear();
							}
							// call messenger for message;
							ke.consume();
						}
					}
				});

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				try {
					ClientUI.this.client.logout(ClientUI.this.userInstance);
					ClientUI.this.client = null;
					ClientUI.this.msgclient = null;
					System.exit(0);
				} catch (final RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == this.loginScene.getEnter()) {
			try {
				if (client == null) {
					this.client = new UserClient(this.ip, this);
					this.client.startClient();
				}
				this.userInstance = this.client.signIn(this.loginScene
						.getUserBox().getText(), this.loginScene.getPwBox()
						.getText().getBytes());
				if (this.userInstance != null) {
					this.msgclient = new MessageClient(this.ip,
							this.userInstance, this);
					this.msgclient.startClient();
					this.stage.setScene(this.msg);
					this.loginScene.clrAll();
				} else {
					this.loginScene.getWarning().setVisible(true);
				}
			} catch (final RemoteException o) {
				this.loginScene.getWarning().setVisible(true);
			} catch (MalformedURLException | NotBoundException o) {
				o.printStackTrace();
			}
		}
		if (e.getSource() == this.servScene.getEnter()) {
			try {
				this.ip = this.servScene.getIpBox().getText();
				this.client = new UserClient(this.ip, this);
				client = null;
				this.servScene.clrAll();
				this.stage.setScene(this.login);
			} catch (MalformedURLException | RemoteException
					| NotBoundException o) {
				this.servScene.getWarning().setVisible(true);
			}
		}
		if (e.getSource() == this.regScene.getEnter()) {
			if (this.regScene.getPwBox().getText()
					.equals(this.regScene.getPwBox2().getText())) {
				try {
					if (client == null) {
						this.client = new UserClient(this.ip, this);
						this.client.startClient();
					}
					if (this.client.register(this.regScene.getUserBox()
							.getText(), this.regScene.getPwBox().getText()
							.getBytes())) {
						this.stage.setScene(this.login);
						this.regScene.clrAll();
						this.loginScene.getWarning().setVisible(false);
					} else {
						this.regScene.getWarning().setVisible(true);
					}
				} catch (final RemoteException o) {
					this.regScene.getWarning().setVisible(true);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == this.msgScene.getEnter()) {
			if (this.msgScene.getInput().getText() != null
					&& !this.msgScene.getInput().getText().trim().isEmpty()) {
				try {
					this.msgclient.sendMessage(this.msgScene.getInput()
							.getText());
				} catch (final RemoteException o) {
					o.printStackTrace();
				} catch (final NotLoggedInException o) {
					try {
						this.client.logout(this.userInstance);
						this.msgclient.stopClient();
						this.client = null;
						this.msgclient = null;
						this.stage.setScene(this.login);
						this.msgScene.clrAll();
					} catch (final RemoteException e1) {
						e1.printStackTrace();
					}
				}
				this.msgScene.getInput().clear();
			}
		}

	}
}