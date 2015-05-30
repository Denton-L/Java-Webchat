package webchat.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import webchat.networking.StartServer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ServerWindow extends Application {

	Image image1 = new Image("/res/plshelp.jpg", true);
	ImageView imview = new ImageView();
	final Desktop desktop = Desktop.getDesktop();
	final FileChooser fileChooser = new FileChooser();
	final Button openDatabase = new Button("Open database file");
	Button createDatabse = new Button("Create databse");
	Button startServer = new Button("Start the server");

	Text fileText;
	Text errorText;

	File database;

	public void start(final Stage primaryStage) {
		StackPane group = new StackPane();
		imview.setImage(image1);
		imview.setFitHeight(600);
		imview.setFitWidth(800);

		VBox vbox = new VBox();
		vbox.setSpacing(15);

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
		addFileChooser(primaryStage, vbox);

		fileText = new Text("No file selected");
		fileText.setId("textstyle2");
		vbox.getChildren().add(fileText);

		vbox.getChildren().add(startServer);

		startServer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				try {
					StartServer.start(database);
				} catch (FileNotFoundException e1) {
					errorText.setText("File could not be found");
					errorText.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					errorText.setText("Database could not be loaded");
					errorText.setVisible(true);
				} catch (AlreadyBoundException e1) {
					errorText.setText("Server could not be started");
					errorText.setVisible(true);
				}
			}
		});

		errorText = new Text("");
		errorText.setVisible(false);
		errorText.setId("textstyle2");
		vbox.getChildren().add(errorText);

		final Scene scene = new Scene(group, 500, 250);

		scene.getStylesheets().add("/res/custom.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void addFileChooser(final Stage stage, VBox vbox) {

		openDatabase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				database = fileChooser.showOpenDialog(stage);
				if (database != null)
					fileText.setText("File chosen: " + database.getName());
			}
		});

		vbox.getChildren().add(openDatabase);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
