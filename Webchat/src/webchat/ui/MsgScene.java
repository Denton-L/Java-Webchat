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
		
		Scene scene = new Scene(group, 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}
	
	/**
	 * Styles and creates input boxes to be added to the object.
	 */
	private void createInputBox() {
		input.setId("inputbox");
		input.setPromptText("Type message here...");
		input.setPrefHeight(300);
		input.setWrapText(true);

		inputbox.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		inputbox.setPadding(new Insets(10, 10, 10, 10));
		inputbox2.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		inputbox2.getChildren().add(logout);
		inputbox2.setAlignment(Pos.CENTER);

		inputbox.setPrefHeight(300);
		inputbox2.setPrefHeight(300);

		BorderPane.setMargin(enter, new Insets(0, 0, 0, 10));
		inputbox.setRight(enter);
		inputbox.setCenter(input);

	}

	/**
	 * Adjusts and sets up the header.
	 */
	public void styleHeader() 
	{
		h.setId("welcome");
		header.setId("header");
		header.setCenter(h);
	}

	/**
	 * Create boxes to assist in layout work.
	 */
	public void createBox() 
	{
		box2.setId("msgbox");
		box2.setPadding(new Insets(10, 10, 10, 10));
		box2.setPrefSize(200, 600);
	}
	
	/**
	 * Add scrollpane to allow scrolling.
	 */
	public void createSP() {
		sp.setContent(msgs);
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setPrefHeight(600);
		sp2.setContent(box2);
		sp2.setFitToHeight(true);
		sp2.setFitToWidth(true);
		sp2.setPrefSize(200, 599);
		sp2.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp2.setId("sp2");
	}

	/**
	 * Set up layout manager to auto resize elements when window is resized.
	 */
	public void setLayouts() {
	    imview.setImage(image1);
	    imview.setFitHeight(600);
	    imview.setFitWidth(800);
		group.getChildren().add(imview);
		// BorderPane.setMargin(subbox, new Insets(0,0,0,12));

		header.setPadding(new Insets(10, 10, 10, 10));

		subbox2.getChildren().addAll(sp2, inputbox2);
		subbox.getChildren().addAll(sp, inputbox);

		VBox.setMargin(sp, new Insets(15, 10, 15, 15));
		VBox.setMargin(sp2, new Insets(15, 10, 15, 15));

		pane.setLeft(subbox2);
		pane.setCenter(subbox);
		pane.setTop(header);
		
		group.getChildren().add(pane);
	}

	/**
	 * This provides button styling.
	 */
	public void styleButton() {
		logout.setId("logout");
		enter.setPrefHeight(300);
		enter.setId("enterbut");
	}

	/**
	 * Method provides auto scrolling when a new message is added.
	 */
	public void setMsgs() {
		msgs.setId("msgboxs");
		msgs.setPadding(new Insets(10, 10, 10, 10));
		

		msgs.heightProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldvalue,
					Object newValue) {
				if (msgs.getHeight() > 400)
				sp.setVvalue(1);
			}
		});
	}
	
	/**
	 * This method clears all input for next messaging session
	 */
	public void clrAll() {
		msgs.getChildren().clear();
		input.clear();
	}
}
