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
 * RegScene Object
 *
 * Creates and deals with the registration scene.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class RegScene {
	Text warning = new Text("Error with input information!");
	Text title1 = new Text("register");
	VBox box = new VBox();
	VBox box2 = new VBox();
	VBox box3 = new VBox();
	TextField userBox = new TextField();
	PasswordField pwBox = new PasswordField();
	PasswordField pwBox2 = new PasswordField();
	Button enter = new Button();
	BorderPane bpane = new BorderPane();
	GridPane pane = new GridPane();
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();
	StackPane group = new StackPane();

	/**
	 * Sets up elements as necessary in order to create the registration scene.
	 *
	 * @return A Scene object.
	 */
	public Scene createReg() {

		this.imview.setFitHeight(600);
		this.imview.setFitWidth(800);
		this.imview.setImage(this.image1);
		this.group.getChildren().add(this.imview);

		this.pane.setPadding(new Insets(10, 15, 10, 15));
		this.pane.setAlignment(Pos.CENTER);
		this.pane.setVgap(10);
		this.pane.setHgap(10);

		this.title1.setId("welcome");
		this.warning.setId("textstyle2");

		this.userBox.setPrefWidth(350);
		this.userBox.setPromptText("Username");
		this.userBox.setPrefHeight(45);

		this.pwBox.setPromptText("Password");
		this.pwBox.setPrefHeight(45);

		this.pwBox2.setPromptText("Confirm Password");
		this.pwBox2.setPrefHeight(45);

		this.enter.setText(">");
		this.enter.setPrefSize(50, 50);

		this.pane.add(this.title1, 0, 0);
		this.pane.add(this.userBox, 0, 1);
		this.pane.add(this.pwBox, 0, 2);
		this.pane.add(this.pwBox2, 0, 3);
		this.pane.add(this.enter, 1, 3);
		this.pane.add(this.warning, 0, 4);

		this.warning.setVisible(false);

		this.bpane.setCenter(this.pane);
		this.group.getChildren().add(this.bpane);

		// create scene
		final Scene scene = new Scene(this.group, 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}

	/**
	 * Clears text for next messaging session.
	 */
	public void clrAll() {
		this.userBox.setText("");
		this.pwBox2.setText("");
		this.pwBox.setText("");
	}
}
