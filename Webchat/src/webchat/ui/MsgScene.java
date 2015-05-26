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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MsgScene {
	BorderPane pane = new BorderPane();
	ScrollPane sp2 = new ScrollPane();
	ScrollPane sp = new ScrollPane();
	
	Text msg = new Text();
	
	VBox box2 = new VBox();
	VBox subbox = new VBox();
	VBox subbox2 = new VBox();
	
	BorderPane inputbox = new BorderPane();
	HBox inputbox2 = new HBox();
	
	HBox header = new HBox();
	
	Text h = new Text("chat");
	
	TextArea input = new TextArea();
	VBox msgs = new VBox();
	
	Button enter = new Button("E");
	Text logout = new Text("exit");
	
	public Scene createMsg() {
		styleHeader();
		createBox();
		createSP();
		setLayouts();
		createInputBox();
		styleButton();
		setMsgs();
		
		Scene scene = new Scene(pane, 800, 600);
		scene.getStylesheets().add("/res/custom.css");
		return scene;
	}
	
	private void createInputBox() {
		input.setId("inputbox");
		input.setPromptText("Type message here...");
		input.setFont(MainInterface.getFont());
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
	
	public void styleHeader() {
		h.setId("welcome");
		
		header.setId("header");
		header.getChildren().addAll(h);
		header.setAlignment(Pos.CENTER);
	}
	
	public void createBox() {
		box2.setId("msgbox");
		box2.setPadding(new Insets(10, 10, 10, 10));
		box2.setPrefSize(200, 600);
	}
	
	public void createSP() {
		sp.setContent(msgs);
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setPrefHeight(600);
		sp2.setContent(box2);
		sp2.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp2.setId("sp2");
	}
	
	public void setLayouts() {
		// BorderPane.setMargin(subbox, new Insets(0,0,0,12));
		
		header.setPadding(new Insets(10, 10, 10, 10));
		
		subbox2.getChildren().addAll(sp2, inputbox2);
		subbox.getChildren().addAll(sp, inputbox);
		
		VBox.setMargin(sp, new Insets(15, 10, 15, 15));
		VBox.setMargin(sp2, new Insets(15, 10, 15, 15));
		
		Image image1 = new Image("/res/plshelp.jpg", true);
		BackgroundSize bgsize = new BackgroundSize(800, 600, false, false,
				true, true);
		
		BackgroundImage myBI = new BackgroundImage(image1,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, bgsize);
		
		pane.setBackground(new Background(myBI));
		pane.setLeft(subbox2);
		pane.setCenter(subbox);
		pane.setTop(header);
		
	}
	
	public void styleButton() {
		logout.setFont(MainInterface.getFont3());
		logout.setId("logout");
		
		enter.setPrefHeight(300);
	}
	
	public void setMsgs() {
		msgs.heightProperty().addListener(new ChangeListener<Object>() {
			
			@Override
			public void changed(ObservableValue<?> observable, Object oldvalue,
					Object newValue) {
				
				sp.setVvalue((Double) newValue);
			}
		});
		
		msgs.setId("msgboxs");
		msgs.setPadding(new Insets(10, 10, 10, 10));
	}
	
	public void clrAll() {
		msgs.getChildren().clear();
		input.clear();
	}
}
