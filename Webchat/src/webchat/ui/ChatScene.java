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
 * A login scene and properties.
 *
 * @author Jing Yi Xie
 * @version 2015-05-25
 */
public class ChatScene {

	/** A text title for the scene. */
	private final Text title = new Text("welcome");
	/** A text warning for when input does not match expected. */
	private Text warning = new Text("Username and/or password incorrect");
	/** A text field to input user name for the scene. */
	private TextField userBox = new TextField();
	/** A text field to input password. */
	private PasswordField pwBox = new PasswordField();
	/** A button to go to next scene. */
	private Button enter = new Button();
	/** A button to go to registration scene. */
	private Button register = new Button();
	/** A gridpane to deal with layout. */
	private final GridPane grid = new GridPane();
	/** A borderpane to deal with layout. */
	private BorderPane pane = new BorderPane();
	/** An image to work as background */
	private final Image image1 = new Image("/background.jpg", true);
	/** An imageview to display the image. */
	private final ImageView imview = new ImageView();
	/** A stackpane so the background can be properly placed. */
	private StackPane group = new StackPane();

	/**
	 * This method adds and styles the necessary elements to create the login
	 * screen.
	 *
	 * @return scene The Login page of the UI.
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
	 * Method clears all input boxes.
	 */
	public void clrAll() {
		this.getUserBox().clear();
		this.getPwBox().clear();
	}

	/**
	 * 
	 * @return enter A Button to change scenes.
	 */
	public Button getEnter() {
		return this.enter;
	}

	/**
	 * 
	 * @param enter
	 *            A Button to change scenes.
	 */
	public void setEnter(Button enter) {
		this.enter = enter;
	}

	/**
	 * 
	 * @return warning A warning for unexpected input.
	 */
	public Text getWarning() {
		return this.warning;
	}

	/**
	 * 
	 * @param warning
	 *            A warning for unexpected input.
	 */
	public void setWarning(Text warning) {
		this.warning = warning;
	}

	/**
	 * 
	 * @return pane A pane to deal with layout.
	 */
	public BorderPane getPane() {
		return this.pane;
	}

	/**
	 * 
	 * @param pane
	 *            A pane to deal with layout.
	 */
	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

	/**
	 * 
	 * @return register A Button to change scenes.
	 */
	public Button getRegister() {
		return this.register;
	}

	/**
	 * 
	 * @param register A Button to change scenes.
	 */
	public void setRegister(Button register) {
		this.register = register;
	}

	/**
	 * 
	 * @return group A stack pane to deal with layers.
	 */
	public StackPane getGroup() {
		return this.group;
	}

	/**
	 * 
	 * @param group A stack pane to deal with layers.
	 */
	public void setGroup(StackPane group) {
		this.group = group;
	}

	/**
	 * 
	 * @return pwBox A Button to change scenes.
	 */
	public PasswordField getPwBox() {
		return this.pwBox;
	}

	/**
	 * 
	 * @param pwBox A password field for the user to enter password.
	 */
	public void setPwBox(PasswordField pwBox) {
		this.pwBox = pwBox;
	}

	/**
	 * 
	 * @return userBox A user field to receive input.
	 */
	public TextField getUserBox() {
		return this.userBox;
	}

	/**
	 * 
	 * @param userBox A user field to receive input.
	 */
	public void setUserBox(TextField userBox) {
		this.userBox = userBox;
	}
}
