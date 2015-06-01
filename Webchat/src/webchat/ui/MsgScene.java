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
	private StackPane group = new StackPane();
	/**
	 * Loads and places images
	 */
	private final Image image1 = new Image("/plshelp.jpg", true);
	private final ImageView imview = new ImageView();
	/**
	 * Creates borderpane layout
	 */
	private final BorderPane pane = new BorderPane();
	/**
	 * Creates scrollpane layout
	 */
	private final ScrollPane sp2 = new ScrollPane();
	private final ScrollPane sp = new ScrollPane();
	
	private VBox box2 = new VBox();
	private final VBox subbox = new VBox();
	private final VBox subbox2 = new VBox();
	
	private final BorderPane inputbox = new BorderPane();
	
	private final HBox inputbox2 = new HBox();
	private BorderPane header = new BorderPane();
	
	private final Text h = new Text("chat");
	private Text logout = new Text("exit");
	
	private TextArea input = new TextArea();
	private VBox msgs = new VBox();
	
	private Button enter = new Button(">");
	
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
		
		final Scene scene = new Scene(this.getGroup(), 800, 600);
		scene.getStylesheets().add("/custom.css");
		return scene;
	}
	
	/**
	 * Styles and creates input boxes to be added to the object.
	 */
	private void createInputBox() {
		this.getInput().setId("inputbox");
		this.getInput().setPromptText("Type message here...");
		this.getInput().setPrefHeight(300);
		this.getInput().setWrapText(true);
		
		this.inputbox.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		this.inputbox.setPadding(new Insets(10, 10, 10, 10));
		this.inputbox2.setStyle("-fx-background-color: rgba(0,0,0,0.2)");
		this.inputbox2.getChildren().add(this.getLogout());
		this.inputbox2.setAlignment(Pos.CENTER);
		
		this.inputbox.setPrefHeight(300);
		this.inputbox2.setPrefHeight(300);
		
		BorderPane.setMargin(this.getEnter(), new Insets(0, 0, 0, 10));
		this.inputbox.setRight(this.getEnter());
		this.inputbox.setCenter(this.getInput());
		
	}
	
	/**
	 * Adjusts and sets up the header.
	 */
	public void styleHeader() {
		this.h.setId("welcome");
		this.getHeader().setId("header");
		this.getHeader().setCenter(this.h);
	}
	
	/**
	 * Create boxes to assist in layout work.
	 */
	public void createBox() {
		this.getBox2().setId("msgbox");
		this.getBox2().setPadding(new Insets(10, 10, 10, 10));
		this.getBox2().setPrefSize(200, 600);
	}
	
	/**
	 * Add scrollpane to allow scrolling.
	 */
	public void createSP() {
		this.sp.setContent(this.getMsgs());
		this.sp.setFitToHeight(true);
		this.sp.setFitToWidth(true);
		this.sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.sp.setPrefHeight(600);
		this.sp2.setContent(this.getBox2());
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
		this.getGroup().getChildren().add(this.imview);
		// BorderPane.setMargin(subbox, new Insets(0,0,0,12));
		
		this.getHeader().setPadding(new Insets(10, 10, 10, 10));
		
		this.subbox2.getChildren().addAll(this.sp2, this.inputbox2);
		this.subbox.getChildren().addAll(this.sp, this.inputbox);
		
		VBox.setMargin(this.sp, new Insets(15, 10, 15, 15));
		VBox.setMargin(this.sp2, new Insets(15, 10, 15, 15));
		
		this.pane.setLeft(this.subbox2);
		this.pane.setCenter(this.subbox);
		this.pane.setTop(this.getHeader());
		
		this.getGroup().getChildren().add(this.pane);
	}
	
	/**
	 * This provides button styling.
	 */
	public void styleButton() {
		this.getLogout().setId("logout");
		this.getEnter().setPrefHeight(300);
		this.getEnter().setId("enterbut");
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
		this.getMsgs().getChildren().clear();
		this.getInput().clear();
	}
	
	public VBox getBox2() {
		return this.box2;
	}
	
	public void setBox2(VBox box2) {
		this.box2 = box2;
	}
	
	public VBox getMsgs() {
		return this.msgs;
	}
	
	public void setMsgs(VBox msgs) {
		this.msgs = msgs;
	}
	
	public Button getEnter() {
		return this.enter;
	}
	
	public void setEnter(Button enter) {
		this.enter = enter;
	}
	
	public Text getLogout() {
		return this.logout;
	}
	
	public void setLogout(Text logout) {
		this.logout = logout;
	}
	
	public TextArea getInput() {
		return this.input;
	}
	
	public void setInput(TextArea input) {
		this.input = input;
	}
	
	public BorderPane getHeader() {
		return this.header;
	}
	
	public void setHeader(BorderPane header) {
		this.header = header;
	}
	
	public StackPane getGroup() {
		return this.group;
	}
	
	public void setGroup(StackPane group) {
		this.group = group;
	}
}
