package webchat.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
	
	public Scene createReg() {
		// background
		Image image1 = new Image("/res/plshelp.jpg", true);
		BackgroundSize bgsize = new BackgroundSize(800, 600, false, false,
				true, true);
		
		BackgroundImage myBI = new BackgroundImage(image1,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, bgsize);
		
		// create BorderPane
		pane.setPadding(new Insets(10, 15, 10, 15));
		pane.setBackground(new Background(myBI));
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(10);
		
		title1.setId("welcome");
		warning.setId("textstyle2");
		
		userBox.setPrefWidth(350);
		userBox.setPromptText("Username");
		userBox.setPrefHeight(40);
		userBox.setFont(MainInterface.getFont());
		
		pwBox.setPromptText("Password");
		pwBox.setFont(MainInterface.getFont());
		
		pwBox2.setPromptText("Confirm Password");
		pwBox2.setFont(MainInterface.getFont());
		
		enter.setText(">");
		
		pane.add(title1, 0, 0);
		pane.add(userBox, 0, 1);
		pane.add(pwBox, 0, 2);
		pane.add(pwBox2, 0, 3);
		pane.add(enter, 1, 3);
		pane.add(warning, 0, 4);
		
		warning.setVisible(false);
		
		// create scene
		Scene scene = new Scene(pane, 800, 600);
		scene.getStylesheets().add("/res/custom.css");
		return scene;
	}
	
	public void clrAll() {
		userBox.setText("");
		pwBox2.setText("");
		pwBox.setText("");
	}
}
