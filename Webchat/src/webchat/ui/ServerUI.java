package webchat.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.StageStyle;
import webchat.database.UserDatabase;
import webchat.networking.StartServer;

public class ServerUI extends Application {
	
	private final Image image1 = new Image("/background.jpg", true);
	private final ImageView imview = new ImageView();
	private final FileChooser fileChooser = new FileChooser();
	private final Button openDatabase = new Button("Open database file");
	private final Button createDatabase = new Button("Create database");
	private final Button startServer = new Button("Start the server");

	private Text fileText;
	private Text errorText;
	private Text selectText;
	private Text createText;
	private File database;
	private double xvar;
	private double yvar;
	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Server Starter");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		final StackPane group = new StackPane();
		this.imview.setImage(this.image1);
		this.imview.setFitHeight(600);
		this.imview.setFitWidth(800);
		
		final VBox vbox = new VBox();
		vbox.setSpacing(15);
		
		final HBox hbox1 = new HBox();
		hbox1.setSpacing(15);
		
		final HBox hbox2 = new HBox();
		hbox2.setSpacing(15);
		
		final HBox hbox3 = new HBox();
		hbox3.setSpacing(15);
		
		final GridPane pane = new GridPane();
		//pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(0,0,15,15));
		pane.add(vbox, 1, 1);

		group.getChildren().add(this.imview);
		group.getChildren().add(pane);

		Text close = new Text("x");
		Text min = new Text("_");
		close.setId("welcome");
		min.setId("welcome");
		close.setStyle("-fx-font-size: 30");
		min.setStyle("-fx-font-size: 30");
		HBox titlebar = new HBox();
		HBox.setMargin(close, new Insets(0,10,0,15));
		HBox.setMargin(min, new Insets(0,0,0,15));
		titlebar.setAlignment(Pos.CENTER_RIGHT);
		titlebar.setPrefWidth(250);
		titlebar.getChildren().addAll(min,close);

		close.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				primaryStage.close();
			}

		});

		min.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				primaryStage.setIconified(true);
			}

		});
		
		group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xvar= event.getSceneX();
				yvar= event.getSceneY();
			}
		});

		group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xvar);
				primaryStage.setY(event.getScreenY() - yvar);
			}
		});

		pane.add(titlebar,1,0);
		
		Text ipaddress;
		try {
			ipaddress = new Text("The server IP address is: "
					+ InetAddress.getLocalHost().getHostAddress());
			ipaddress.setId("textstyle2");
			vbox.getChildren().add(ipaddress);
		} catch (final UnknownHostException e) {
			e.printStackTrace();
		}
		addFileChooser(primaryStage, hbox1, hbox2);
		
		this.fileText = new Text("No file selected");
		this.fileText.setId("textstyle2");
		hbox1.getChildren().add(this.fileText);
		
		this.createText = new Text("");
		this.createText.setVisible(false);
		this.createText.setId("textstyle2");
		hbox2.getChildren().add(this.createText);
		
		this.selectText = new Text("Select a file before starting the server");
		this.selectText.setId("textstyle2");
		
		vbox.getChildren().addAll(hbox1, hbox2, hbox3);
		this.startServer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				if (ServerUI.this.database != null) {
					try {
						StartServer.start(ServerUI.this.database);
						ServerUI.this.selectText.setText("Server started");
					} catch (final FileNotFoundException e1) {
						ServerUI.this.errorText
						.setText("File could not be found");
						ServerUI.this.errorText.setVisible(true);
						e1.printStackTrace();
					} catch (final ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (final IOException e1) {
						ServerUI.this.errorText
						.setText("Database could not be loaded");
						ServerUI.this.errorText.setVisible(true);
						e1.printStackTrace();
					} catch (final AlreadyBoundException e1) {
						ServerUI.this.errorText
						.setText("Server could not be started");
						ServerUI.this.errorText.setVisible(true);
						e1.printStackTrace();
					}
				} else {
					ServerUI.this.errorText.setText("No file selected");
					ServerUI.this.errorText.setVisible(true);
				}
			}
		});
		
		hbox3.getChildren().add(this.startServer);
		
		this.errorText = new Text("");
		this.errorText.setVisible(false);
		this.errorText.setId("textstyle2");
		hbox3.getChildren().add(this.errorText);
		
		vbox.getChildren().add(this.selectText);
		
		final Scene scene = new Scene(group, 405, 270);
		
		scene.getStylesheets().add("/custom.css");
		
		primaryStage.setScene(scene);
		//primaryStage.getIcons().add(new Image("/IMAGEGOESHERE.jpg" ));
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
	}
	
	private void addFileChooser(final Stage stage, HBox hbox1, HBox hbox2) {
		// TODO check if this works for runnable jars
		this.fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("SER file", "*.ser"));
		this.fileChooser.setInitialDirectory(new File(System
				.getProperty("user.dir") + "/res/"));
		this.openDatabase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				ServerUI.this.fileChooser.setTitle("Select Database file");
				ServerUI.this.database = ServerUI.this.fileChooser
						.showOpenDialog(stage);
				if (ServerUI.this.database != null) {
					ServerUI.this.fileText.setText("File chosen: "
							+ ServerUI.this.database.getName());
					ServerUI.this.selectText.setText("");
					ServerUI.this.createText.setText("");
				}
			}
		});
		
		this.createDatabase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				ServerUI.this.fileChooser.setTitle("Create Database");
				ServerUI.this.database = ServerUI.this.fileChooser
						.showSaveDialog(stage);
				if (ServerUI.this.database != null) {
					final int i = ServerUI.this.database.toString()
							.lastIndexOf('.');
					final int p = Math.max(ServerUI.this.database.toString()
							.lastIndexOf('/'), ServerUI.this.database
							.toString().lastIndexOf('\\'));
					if (i < p) {
						ServerUI.this.database = new File(
								ServerUI.this.database.toString() + ".ser");
					}
					if (ServerUI.this.database
							.toString()
							.substring(
									ServerUI.this.database.toString()
									.lastIndexOf('.') + 1)
									.equals("ser")) {
						
						try {
							new UserDatabase()
							.saveDatabase(ServerUI.this.database);
							ServerUI.this.createText
							.setText("Database file created");
							ServerUI.this.createText.setVisible(true);
							ServerUI.this.fileText.setText("File chosen: "
									+ ServerUI.this.database.getName());
							ServerUI.this.selectText.setText("");
						} catch (final FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (final IOException e1) {
							e1.printStackTrace();
						}
					} else {
						ServerUI.this.createText
						.setText("Incorrect file format");
						ServerUI.this.createText.setVisible(true);
					}
					
				}
				
			}
		});
		
		hbox1.getChildren().add(this.openDatabase);
		hbox2.getChildren().add(this.createDatabase);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
