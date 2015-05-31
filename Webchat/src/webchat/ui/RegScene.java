package webchat.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	GridPane pane = new GridPane();
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();

	/**
	 * Sets up elements as necessary in order to create the registration scene.
	 * @return A Scene object.
	 */
	public Scene createReg() {
		
		StackPane group = new StackPane();		
		imview.setFitHeight(600);
	    imview.setFitWidth(800);
        imview.setImage(image1);
		group.getChildren().add(imview);
		
		pane.setPadding(new Insets(10, 15, 10, 15));
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(10);

		title1.setId("welcome");
		warning.setId("textstyle2");

		userBox.setPrefWidth(350);
		userBox.setPromptText("Username");
		userBox.setPrefHeight(45);

		pwBox.setPromptText("Password");
		pwBox.setPrefHeight(45);

		pwBox2.setPromptText("Confirm Password");
		pwBox2.setPrefHeight(45);

		enter.setText(">");
		enter.setPrefSize(50, 50);

		pane.add(title1, 0, 0);
		pane.add(userBox, 0, 1);
		pane.add(pwBox, 0, 2);
		pane.add(pwBox2, 0, 3);
		pane.add(enter, 1, 3);
		pane.add(warning, 0, 4);

		warning.setVisible(false);
		
		group.getChildren().add(pane);

		// create scene
		Scene scene = new Scene(group, 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}

	/**
	 * Clears text for next messaging session.
	 */
	public void clrAll() {
		userBox.setText("");
		pwBox2.setText("");
		pwBox.setText("");
	}
}
