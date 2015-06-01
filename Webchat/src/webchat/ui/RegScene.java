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
 * RegScene Object
 *
 * Creates and deals with the registration scene.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class RegScene {
	private Text warning = new Text("Error with input information!");
	private final Text title1 = new Text("register");
	private TextField userBox = new TextField();
	private PasswordField pwBox = new PasswordField();
	private PasswordField pwBox2 = new PasswordField();
	private Button enter = new Button();
	private BorderPane bpane = new BorderPane();
	private final GridPane pane = new GridPane();
	private final Image image1 = new Image("/plshelp.jpg", true);
	private final ImageView imview = new ImageView();
	private StackPane group = new StackPane();
	
	/**
	 * Sets up elements as necessary in order to create the registration scene.
	 *
	 * @return A Scene object.
	 */
	public Scene createReg() {
		
		this.imview.setFitHeight(600);
		this.imview.setFitWidth(800);
		this.imview.setImage(this.image1);
		this.getGroup().getChildren().add(this.imview);
		
		this.pane.setPadding(new Insets(10, 15, 10, 15));
		this.pane.setAlignment(Pos.CENTER);
		this.pane.setVgap(10);
		this.pane.setHgap(10);
		
		this.title1.setId("welcome");
		this.getWarning().setId("textstyle2");
		
		this.getUserBox().setPrefWidth(350);
		this.getUserBox().setPromptText("Username");
		this.getUserBox().setPrefHeight(45);
		
		this.getPwBox().setPromptText("Password");
		this.getPwBox().setPrefHeight(45);
		
		this.getPwBox2().setPromptText("Confirm Password");
		this.getPwBox2().setPrefHeight(45);
		
		this.getEnter().setText(">");
		this.getEnter().setPrefSize(50, 50);
		
		this.pane.add(this.title1, 0, 0);
		this.pane.add(this.getUserBox(), 0, 1);
		this.pane.add(this.getPwBox(), 0, 2);
		this.pane.add(this.getPwBox2(), 0, 3);
		this.pane.add(this.getEnter(), 1, 3);
		this.pane.add(this.getWarning(), 0, 4);
		
		this.getWarning().setVisible(false);
		
		this.getBpane().setCenter(this.pane);
		this.getGroup().getChildren().add(this.getBpane());
		
		// create scene
		final Scene scene = new Scene(this.getGroup(), 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}
	
	/**
	 * Clears text for next messaging session.
	 */
	public void clrAll() {
		this.getUserBox().setText("");
		this.getPwBox2().setText("");
		this.getPwBox().setText("");
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
	
	public BorderPane getBpane() {
		return this.bpane;
	}
	
	public void setBpane(BorderPane bpane) {
		this.bpane = bpane;
	}
	
	public StackPane getGroup() {
		return this.group;
	}
	
	public void setGroup(StackPane group) {
		this.group = group;
	}
	
	public PasswordField getPwBox2() {
		return this.pwBox2;
	}
	
	public void setPwBox2(PasswordField pwBox2) {
		this.pwBox2 = pwBox2;
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
