package webchat.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ChatScene object
 *
 * Creates and deals with the login scene.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class ChatScene {

	Text title = new Text("welcome");
	Text warning = new Text("Username and/or password incorrect");
	TextField userBox = new TextField();
	PasswordField pwBox = new PasswordField();
	Button enter = new Button();
	Button register = new Button();
	VBox box = new VBox();
	GridPane grid = new GridPane();
	BorderPane pane = new BorderPane();
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();
	StackPane group = new StackPane();

	/**
	 * This method adds and styles the necessary elements to create the login
	 * screen.
	 *
	 * @return the Scene object, Login Screen
	 */
	public Scene createChat() {
		this.imview.setImage(this.image1);
		this.imview.setFitHeight(600);
		this.imview.setFitWidth(800);
		this.group.getChildren().add(this.imview);

		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(100, 100, 100, 100));

		this.title.setId("welcome");
		this.warning.setId("textstyle2");
		this.grid.add(this.title, 0, 0, 2, 1);

		this.userBox.setPrefHeight(45);
		this.userBox.setPromptText("Username");
		this.userBox.setPrefWidth(400);
		this.grid.add(this.userBox, 1, 1);

		this.pwBox.setPrefHeight(45);
		this.pwBox.setPromptText("Password");
		this.grid.add(this.pwBox, 1, 2);

		this.enter.setText(">");
		this.enter.setPrefSize(50, 50);
		this.register.setText("R");
		this.register.setPrefSize(50, 50);

		this.grid.add(this.enter, 2, 2);
		this.grid.add(this.register, 2, 1);
		this.grid.add(this.warning, 1, 4);

		this.warning.setVisible(false);

		this.pane.setCenter(this.grid);
		this.group.getChildren().add(this.pane);

		// create scene
		final Scene scene = new Scene(this.group, 800, 600);
		scene.getStylesheets().add("/custom.css");

		return scene;
	}

	/**
	 * Method clears all input boxes
	 */
	public void clrAll() {
		this.userBox.clear();
		this.pwBox.clear();
	}
}
