package webchat.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;

import javax.imageio.ImageIO;

import webchat.database.UserDatabase;
import webchat.networking.StartServer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ServerUI extends Application {

	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();
	final Desktop desktop = Desktop.getDesktop();
	final FileChooser fileChooser = new FileChooser();
	final Button openDatabase = new Button("Open database file");
	Button createDatabase = new Button("Create database");
	Button startServer = new Button("Start the server");

	Text fileText;
	Text errorText;
	Text selectText;
	Text createText;
	File database;

	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Server Starter");
		StackPane group = new StackPane();
		imview.setImage(image1);
		imview.setFitHeight(600);
		imview.setFitWidth(800);

		VBox vbox = new VBox();
		vbox.setSpacing(15);

		HBox hbox1 = new HBox();
		hbox1.setSpacing(15);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(15);

		HBox hbox3 = new HBox();
		hbox3.setSpacing(15);

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.add(vbox, 1, 1);

		group.getChildren().add(imview);
		group.getChildren().add(pane);

		Text ipaddress;
		try {
			ipaddress = new Text("The server IP address is: "
					+ InetAddress.getLocalHost().getHostAddress());
			ipaddress.setId("textstyle2");
			vbox.getChildren().add(ipaddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addFileChooser(primaryStage, hbox1, hbox2);

		fileText = new Text("No file selected");
		fileText.setId("textstyle2");
		hbox1.getChildren().add(fileText);

		createText = new Text("");
		createText.setVisible(false);
		createText.setId("textstyle2");
		hbox2.getChildren().add(createText);

		selectText = new Text("Select a file before starting the server");
		selectText.setId("textstyle2");

		vbox.getChildren().addAll(hbox1, hbox2, hbox3);
		startServer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				if (database != null) {
					try {
						StartServer.start(database);
						selectText.setText("Server started");
					} catch (FileNotFoundException e1) {
						errorText.setText("File could not be found");
						errorText.setVisible(true);
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						errorText.setText("Database could not be loaded");
						errorText.setVisible(true);
						e1.printStackTrace();
					} catch (AlreadyBoundException e1) {
						errorText.setText("Server could not be started");
						errorText.setVisible(true);
						e1.printStackTrace();
					}
				} else {
					errorText.setText("No file selected");
					errorText.setVisible(true);
				}
			}
		});


		hbox3.getChildren().add(startServer);

		errorText = new Text("");
		errorText.setVisible(false);
		errorText.setId("textstyle2");
		hbox3.getChildren().add(errorText);

		vbox.getChildren().add(selectText);

		final Scene scene = new Scene(group, 500, 250);

		scene.getStylesheets().add("/custom.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addFileChooser(final Stage stage, HBox hbox1, HBox hbox2) {
		// TODO check if this works for runnable jars
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/res/"));
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("SER file", "*.ser"));

		openDatabase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				fileChooser.setTitle("Select Database file");
				database = fileChooser.showOpenDialog(stage);
				if (database != null) {
					fileText.setText("File chosen: " + database.getName());
					selectText.setText("");
					createText.setText("");
				}
			}
		});

		createDatabase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				fileChooser.setTitle("Create Database");
				database = fileChooser.showSaveDialog(stage);
				if (database != null) {
					int i = database.toString().lastIndexOf('.');
					int p = Math.max(database.toString().lastIndexOf('/'),
							database.toString().lastIndexOf('\\'));
					if (i < p) {
						database = new File(database.toString() + ".ser");
					}
					if (database
							.toString()
							.substring(database.toString().lastIndexOf('.') + 1)
							.equals("ser")) {

						try {
							new UserDatabase().saveDatabase(database);
							createText.setText("Database file created");
							createText.setVisible(true);
							fileText.setText("File chosen: "
									+ database.getName());
							selectText.setText("");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						createText.setText("Incorrect file format");
						createText.setVisible(true);
					}

				}

			}
		});

		hbox1.getChildren().add(openDatabase);
		hbox2.getChildren().add(createDatabase);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
