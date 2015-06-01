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
	 * This method adds and styles the necessary elements to create the login screen. 
	 * @return the Scene object, Login Screen 
	 */
	public Scene createChat() {		
        imview.setImage(image1);
        imview.setFitHeight(600);
	    imview.setFitWidth(800);
		group.getChildren().add(imview);
        
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(100, 100, 100, 100));

		title.setId("welcome");
		warning.setId("textstyle2");
		grid.add(title, 0, 0, 2, 1);

		userBox.setPrefHeight(45);
		userBox.setPromptText("Username");
		userBox.setPrefWidth(400);
		grid.add(userBox, 1, 1);

		pwBox.setPrefHeight(45);
		pwBox.setPromptText("Password");
		grid.add(pwBox, 1, 2);

		enter.setText(">");
		enter.setPrefSize(50, 50);
		register.setText("R");
		register.setPrefSize(50, 50);

		grid.add(enter, 2, 2);
		grid.add(register, 2, 1);
		grid.add(warning, 1, 4);

		warning.setVisible(false);

		pane.setCenter(grid);
		group.getChildren().add(pane);
		
		// create scene
		Scene scene = new Scene(group, 800,600);
		scene.getStylesheets().add("/custom.css");

		return scene;
	}

	/**
	 * Method clears all input boxes
	 */
	public void clrAll() {
		userBox.clear();
		pwBox.clear();
	}
}
