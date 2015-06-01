package webchat.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ServScene 
{
	Text title = new Text("welcome");
	Text warning = new Text("Incorrect server location");
	TextField ipBox = new TextField();
	Button enter = new Button();
	Button register = new Button();
	BorderPane pane = new BorderPane();
	GridPane grid = new GridPane();
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();
	StackPane group = new StackPane();	

	/**
	 * This method adds and styles the necessary elements to create the login screen. 
	 * @return the Scene object, Login Screen 
	 */
	public Scene createServ() 
	{    
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
		ipBox.setPromptText("Server location");
		ipBox.setPrefWidth(400);
		grid.add(ipBox, 1, 1);

		enter.setText(">");
		enter.setPrefSize(50, 50);

		grid.add(enter, 2, 1);
		grid.add(warning, 1, 2);
		
		pane.setCenter(grid);

		warning.setVisible(false);

		group.getChildren().add(pane);
		
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
