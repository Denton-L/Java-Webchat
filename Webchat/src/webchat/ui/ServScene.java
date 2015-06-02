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

/**
 * A server scene and properties.
 *
 * @author Jing Yi Xie
 * @version 2015-05-25
 */
public class ServScene {
	/**
	 * The title text.
	 */
	private final Text title = new Text("welcome");
	/**
	 * The warning text for unexpected user input.
	 */
	private Text warning = new Text("Incorrect server location");
	/**
	 * The textfield to hold user ip address.
	 */
	private TextField ipBox = new TextField();
	/**
	 * The enter button to change scenes.
	 */
	private Button enter = new Button();
	/**
	 * The borderpane to work with layouts.
	 */
	private BorderPane pane = new BorderPane();
	/**
	 * The gridpane to work with layouts.
	 */
	private final GridPane grid = new GridPane();
	/**
	 * The image to serve as background.
	 */
	private final Image image1 = new Image("/background.jpg", true);
	/**
	 * The image node to hold the image.
	 */
	private final ImageView imview = new ImageView();
	/**
	 * The stack pane to allow things to be placed on top of the background.
	 */
	private StackPane group = new StackPane();
	
	/**
	 * This method adds and styles the necessary elements to create the login
	 * screen.
	 *
	 * @return scene The login screen.
	 */
	public Scene createServ() {
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
		
		this.getIpBox().setPrefHeight(45);
		this.getIpBox().setPromptText("Server location");
		this.getIpBox().setPrefWidth(400);
		this.grid.add(this.getIpBox(), 1, 1);
		
		this.getEnter().setText(">");
		this.getEnter().setPrefSize(50, 50);
		
		this.grid.add(this.getEnter(), 2, 1);
		this.grid.add(this.getWarning(), 1, 2);
		
		this.getPane().setCenter(this.grid);
		
		this.getWarning().setVisible(false);
		
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
		this.getIpBox().clear();
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
	 * @return warning The warning that appears for unexpected user input.
	 */
	public Text getWarning() {
		return this.warning;
	}
	
	/**
	 * 
	 * @param warning The warning that appears for unexpected user input.
	 */
	public void setWarning(Text warning) {
		this.warning = warning;
	}
	
	/**
	 * 
	 * @return pane The borderpane to work with layouts.
	 */
	public BorderPane getPane() {
		return this.pane;
	}
	
	/**
	 * 
	 * @param pane The borderpane to work with layouts.
	 */
	public void setPane(BorderPane pane) {
		this.pane = pane;
	}
	
	/**
	 * 
	 * @return group The stackpane to allow nodes to be placed on top of the background.
	 */
	public StackPane getGroup() {
		return this.group;
	}
	
	/**
	 * 
	 * @param group The stackpane to allow nodes to be placed on top of the background.
	 */
	public void setGroup(StackPane group) {
		this.group = group;
	}
	
	/**
	 * 
	 * @return ipBox The textfield to allow users to input the ip address.
	 */
	public TextField getIpBox() {
		return this.ipBox;
	}
	
	/**
	 * 
	 * @param ipBox The textfield to allow users to input the ip address.
	 */
	public void setIpBox(TextField ipBox) {
		this.ipBox = ipBox;
	}
}
