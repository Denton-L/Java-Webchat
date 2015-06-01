package webchat.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ServScene {
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
	 * This method adds and styles the necessary elements to create the login
	 * screen.
	 *
	 * @return the Scene object, Login Screen
	 */
	public Scene createServ() {
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

		this.ipBox.setPrefHeight(45);
		this.ipBox.setPromptText("Server location");
		this.ipBox.setPrefWidth(400);
		this.grid.add(this.ipBox, 1, 1);

		this.enter.setText(">");
		this.enter.setPrefSize(50, 50);

		this.grid.add(this.enter, 2, 1);
		this.grid.add(this.warning, 1, 2);

		this.pane.setCenter(this.grid);

		this.warning.setVisible(false);

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
		this.ipBox.clear();
	}
}
