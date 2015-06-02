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
import javafx.scene.text.Text;

/**
 * ChatScene object
 *
 * Creates and deals with the login scene.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class LoginScene {
	
	private final Text title = new Text("welcome");
	private Text warning = new Text("Username and/or password incorrect");
	private TextField userBox = new TextField();
	private PasswordField pwBox = new PasswordField();
	private Button enter = new Button();
	private Button register = new Button();
	private final GridPane grid = new GridPane();
	private BorderPane pane = new BorderPane();
	private final Image image1 = new Image("/background.jpg", true);
	private final ImageView imview = new ImageView();
	private StackPane group = new StackPane();
	
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
		this.getGroup().getChildren().add(this.imview);
		
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(100, 100, 100, 100));
		
		this.title.setId("welcome");
		this.getWarning().setId("textstyle2");
		this.grid.add(this.title, 0, 0, 2, 1);
		
		this.getUserBox().setPrefHeight(45);
		this.getUserBox().setPromptText("Username");
		this.getUserBox().setPrefWidth(400);
		this.grid.add(this.getUserBox(), 1, 1);
		
		this.getPwBox().setPrefHeight(45);
		this.getPwBox().setPromptText("Password");
		this.grid.add(this.getPwBox(), 1, 2);
		
		this.getEnter().setText(">");
		this.getEnter().setPrefSize(50, 50);
		this.getRegister().setText("R");
		this.getRegister().setPrefSize(50, 50);
		
		this.grid.add(this.getEnter(), 2, 2);
		this.grid.add(this.getRegister(), 2, 1);
		this.grid.add(this.getWarning(), 1, 4);
		
		this.getWarning().setVisible(false);
		
		this.getPane().setCenter(this.grid);
		this.getGroup().getChildren().add(this.getPane());
		
		// create scene
		final Scene scene = new Scene(this.getGroup(), 800, 600);
		scene.getStylesheets().add("/custom.css");
		
		return scene;
	}
	
	/**
	 * Method clears all input boxes
	 */
	public void clrAll() {
		this.getUserBox().clear();
		this.getPwBox().clear();
	}
	
	public Button getEnter() {
		return this.enter;
	}
	
	public void setEnter(Button enter) {
		this.enter = enter;
	}
	
	public Text getWarning() {
		return this.warning;
	}
	
	public void setWarning(Text warning) {
		this.warning = warning;
	}
	
	public BorderPane getPane() {
		return this.pane;
	}
	
	public void setPane(BorderPane pane) {
		this.pane = pane;
	}
	
	public Button getRegister() {
		return this.register;
	}
	
	public void setRegister(Button register) {
		this.register = register;
	}
	
	public StackPane getGroup() {
		return this.group;
	}
	
	public void setGroup(StackPane group) {
		this.group = group;
	}
	
	public PasswordField getPwBox() {
		return this.pwBox;
	}
	
	public void setPwBox(PasswordField pwBox) {
		this.pwBox = pwBox;
	}
	
	public TextField getUserBox() {
		return this.userBox;
	}
	
	public void setUserBox(TextField userBox) {
		this.userBox = userBox;
	}
}
