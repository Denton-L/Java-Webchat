package ui;

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

public class ChatScene {
	
	Text title = new Text("welcome");
	Text warning = new Text("Username and/or password incorrect");
	TextField userBox = new TextField();
	TextField ipBox = new TextField();
	PasswordField pwBox = new PasswordField();
	Button enter = new Button();
	Button register = new Button();
	VBox box = new VBox();
	GridPane grid = new GridPane();

	public Scene createChat() {
		// background
		Image image1 = new Image("/res/plshelp.jpg", true);
		BackgroundSize bgsize = new BackgroundSize(800, 600, false, false,
				true, true);

		BackgroundImage myBI = new BackgroundImage(image1,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, bgsize);

		// create grid
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(100, 100, 100, 100));
		grid.setBackground(new Background(myBI));

		title.setId("welcome");
		warning.setId("textstyle2");
		grid.add(title, 0, 0, 2, 1);

		userBox.setPromptText("Username");
		userBox.setPrefWidth(400);
		userBox.setFont(MainInterface.getFont());
		grid.add(userBox, 1, 1);

		pwBox.setPromptText("Password");
		pwBox.setFont(MainInterface.getFont());
		grid.add(pwBox, 1, 2);

		ipBox.setPromptText("Server IP");
		ipBox.setFont(MainInterface.getFont());
		grid.add(ipBox, 1, 3);

		enter.setText(">");
		enter.setPrefSize(45, 45);
		register.setText("R");
		register.setPrefSize(45, 45);

		grid.add(enter, 2, 3);
		grid.add(register, 2, 2);
		grid.add(warning, 1, 4);

		warning.setVisible(false);

		// create scene
		Scene scene = new Scene(grid, 800, 600);
		scene.getStylesheets().add("/res/custom.css");

		return scene;
	}

	public void clrAll() {
		userBox.clear();
		pwBox.clear();
		ipBox.clear();
	}
}
