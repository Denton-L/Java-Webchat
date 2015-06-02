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
 * Creates and deals with the registration scene.
 *
 * @author Jing Yi Xie
 * @version 2015-05-25
 */

public class RegScene {
	/**
	 * The text warning that appears when there is unexpected input.
	 */
	private Text warning = new Text("Error with input information!");
	/**
	 * The title of the page.
	 */
	private final Text title1 = new Text("register");
	/**
	 * The textfield to receive the username.
	 */
	private TextField userBox = new TextField();
	/**
	 * The textfield to receive the password.
	 */
	private PasswordField pwBox = new PasswordField();
	/**
	 * The textfield to receive confirmation of the password.
	 */
	private PasswordField pwBox2 = new PasswordField();
	/**
	 * The enter button to change the scene.
	 */
	private Button enter = new Button();
	/**
	 * The layout pane to work with layouts.
	 */
	private BorderPane bpane = new BorderPane();
	/**
	 * The layout pane to work with layouts.
	 */
	private final GridPane pane = new GridPane();
	/**
	 * The background image.
	 */
	private final Image image1 = new Image("/background.jpg", true);
	/**
	 * The image view node to display the bg image.
	 */
	private final ImageView imview = new ImageView();
	/**
	 * The stack pane that allows nodes to placed on top of one another.
	 */
	private StackPane group = new StackPane();
	
	/**
	 * Sets up elements as necessary in order to create the registration scene.
	 *
	 * @return scene The registration scene for the client interface.
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
	
	/**
	 * 
	 * @return enter The button to change scenes.
	 */
	public Button getEnter() {
		return this.enter;
	}
	
	/**
	 * 
	 * @param enter The button to change scenes.
	 */
	public void setEnter(Button enter) {
		this.enter = enter;
	}
	
	/**
	 * 
	 * @return warning The text that appears when unexpected input is received.
	 */
	public Text getWarning() {
		return this.warning;
	}
	
	/**
	 * 
	 * @param warning The text that appears when unexpected input is received.
	 */
	public void setWarning(Text warning) {
		this.warning = warning;
	}
	
	/**
	 * 
	 * @return bpane The borderpane to deal with layouts.
	 */
	public BorderPane getBpane() {
		return this.bpane;
	}

	/**
	 * 
	 * @param bpane The borderpane to deal with layouts.
	 */
	public void setBpane(BorderPane bpane) {
		this.bpane = bpane;
	}
	
	/**
	 * 
	 * @return group The stack pane to allow nodes to be placed atop one another.
	 */
	public StackPane getGroup() {
		return this.group;
	}
	
	/**
	 * 
	 * @param group The stack pane to allow nodes to be placed atop one another.
	 */
	public void setGroup(StackPane group) {
		this.group = group;
	}
	
	/**
	 * 
	 * @return pwBox2 The password field for users to confirm.
	 */
	public PasswordField getPwBox2() {
		return this.pwBox2;
	}
	
	/**
	 * 
	 * @param pwBox2 The password box for users to confirm.
	 */
	public void setPwBox2(PasswordField pwBox2) {
		this.pwBox2 = pwBox2;
	}
	
	/**
	 * 
	 * @return pwBox The password box for users to enter password.
	 */
	public PasswordField getPwBox() {
		return this.pwBox;
	}
	
	/**
	 * 
	 * @param pwBox The password box for users to enter password.
	 */
	public void setPwBox(PasswordField pwBox) {
		this.pwBox = pwBox;
	}
	
	/**
	 * 
	 * @return userBox The textfield for users to enter username.
	 */
	public TextField getUserBox() {
		return this.userBox;
	}
	
	/**
	 * 
	 * @param userBox The textfield for users to enter username.
	 */
	public void setUserBox(TextField userBox) {
		this.userBox = userBox;
	}
}
