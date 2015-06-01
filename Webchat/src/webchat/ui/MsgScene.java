package webchat.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * MsgScene object
 *
 * Creates and deals with the messaging scene.
 *
 * @author Jing Yi Xie
 * @version 1.0
 */

public class MsgScene {

	/**
	 * Creates a stackable box for layout purposes (inserting background image)
	 */
	StackPane group = new StackPane();
	/**
	 * Loads and places images
	 */
	Image image1 = new Image("/plshelp.jpg", true);
	ImageView imview = new ImageView();
	/**
	 * Creates borderpane layout
	 */
	BorderPane pane = new BorderPane();
	/**
	 * Creates scrollpane layout
	 */
	ScrollPane sp2 = new ScrollPane();
	ScrollPane sp = new ScrollPane();

	Text msg = new Text();

	VBox box2 = new VBox();
	VBox subbox = new VBox();
	VBox subbox2 = new VBox();

	BorderPane inputbox = new BorderPane();

	HBox inputbox2 = new HBox();
	BorderPane header = new BorderPane();

	Text h = new Text("chat");
	Text logout = new Text("exit");

	TextArea input = new TextArea();
	VBox msgs = new VBox();

	Button enter = new Button(">");

	/**
	 * Calls all other relevant MsgScene methods to create the final MsgScene.
	 *
	 * @return A Scene object.
	 */

	public Scene createMsg() {

		styleHeader();
		createBox();
		createSP();
		setLayouts();
		createInputBox();
		styleButton();
		setMsgs();

		final Scene scene = new Scene(this.group, 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}

	/**
	 * Styles and creates input boxes to be added to the object.
	 */
	private void createInputBox() {
		this.input.setId("inputbox");
		this.input.setPromptText("Type message here...");
		this.input.setPrefHeight(300);
		this.input.setWrapText(true);

		this.inputbox.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		this.inputbox.setPadding(new Insets(10, 10, 10, 10));
		this.inputbox2.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		this.inputbox2.getChildren().add(this.logout);
		this.inputbox2.setAlignment(Pos.CENTER);

		this.inputbox.setPrefHeight(300);
		this.inputbox2.setPrefHeight(300);

		BorderPane.setMargin(this.enter, new Insets(0, 0, 0, 10));
		this.inputbox.setRight(this.enter);
		this.inputbox.setCenter(this.input);

	}

	/**
	 * Adjusts and sets up the header.
	 */
	public void styleHeader() {
		this.h.setId("welcome");
		this.header.setId("header");
		this.header.setCenter(this.h);
	}

	/**
	 * Create boxes to assist in layout work.
	 */
	public void createBox() {
		this.box2.setId("msgbox");
		this.box2.setPadding(new Insets(10, 10, 10, 10));
		this.box2.setPrefSize(200, 600);
	}

	/**
	 * Add scrollpane to allow scrolling.
	 */
	public void createSP() {
		this.sp.setContent(this.msgs);
		this.sp.setFitToHeight(true);
		this.sp.setFitToWidth(true);
		this.sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.sp.setPrefHeight(600);
		this.sp2.setContent(this.box2);
		this.sp2.setFitToHeight(true);
		this.sp2.setFitToWidth(true);
		this.sp2.setPrefSize(200, 599);
		this.sp2.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.sp2.setId("sp2");
	}

	/**
	 * Set up layout manager to auto resize elements when window is resized.
	 */
	public void setLayouts() {
		this.imview.setImage(this.image1);
		this.imview.setFitHeight(600);
		this.imview.setFitWidth(800);
		this.group.getChildren().add(this.imview);
		// BorderPane.setMargin(subbox, new Insets(0,0,0,12));

		this.header.setPadding(new Insets(10, 10, 10, 10));

		this.subbox2.getChildren().addAll(this.sp2, this.inputbox2);
		this.subbox.getChildren().addAll(this.sp, this.inputbox);

		VBox.setMargin(this.sp, new Insets(15, 10, 15, 15));
		VBox.setMargin(this.sp2, new Insets(15, 10, 15, 15));

		this.pane.setLeft(this.subbox2);
		this.pane.setCenter(this.subbox);
		this.pane.setTop(this.header);

		this.group.getChildren().add(this.pane);
	}

	/**
	 * This provides button styling.
	 */
	public void styleButton() {
		this.logout.setId("logout");
		this.enter.setPrefHeight(300);
		this.enter.setId("enterbut");
	}

	/**
	 * Method provides auto scrolling when a new message is added.
	 */
	public void setMsgs() {
		this.msgs.setId("msgboxs");
		this.msgs.setPadding(new Insets(10, 10, 10, 10));

		this.msgs.heightProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldvalue,
					Object newValue) {
				if (MsgScene.this.msgs.getHeight() > 400) {
					MsgScene.this.sp.setVvalue(1);
				}
			}
		});
	}

	/**
	 * This method clears all input for next messaging session
	 */
	public void clrAll() {
		this.msgs.getChildren().clear();
		this.input.clear();
	}
}
