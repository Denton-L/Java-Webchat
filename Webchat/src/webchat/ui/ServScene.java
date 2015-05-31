package webchat.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ServScene 
{
	Text title = new Text("welcome");
	Text warning = new Text("Incorrect server IP");
	TextField ipBox = new TextField();
	Button enter = new Button();
	Button register = new Button();
	VBox box = new VBox();
	GridPane grid = new GridPane();
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();

	/**
	 * This method adds and styles the necessary elements to create the login screen. 
	 * @return the Scene object, Login Screen 
	 */
	public Scene createServ() {		
		StackPane group = new StackPane();		
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

		ipBox.setPrefHeight(45);
		ipBox.setPromptText("Server IP");
		ipBox.setPrefWidth(400);
		grid.add(ipBox, 1, 1);

		enter.setText(">");
		enter.setPrefSize(50, 50);

		grid.add(enter, 2, 1);
		grid.add(warning, 1, 2);

		warning.setVisible(false);

		group.getChildren().add(grid);
		
		// create scene
		Scene scene = new Scene(group, 800, 600);
		scene.getStylesheets().add("/custom.css");

		return scene;
	}

	/**
	 * Method clears all input boxes
	 */
	public void clrAll() {
		ipBox.clear();
	}
}
