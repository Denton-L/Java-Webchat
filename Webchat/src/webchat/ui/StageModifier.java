package webchat.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageModifier 
{
	ServScene servscene;
	MsgScene msgscene;
	ChatScene chatscene;
	RegScene regscene;
	Stage stage;
	double xvar, yvar;
	
	public StageModifier (ServScene servscene1, MsgScene msgscene1, ChatScene chatscene1, RegScene regscene1, Stage stage1)
	{
		servscene = servscene1;
		msgscene = msgscene1;
		chatscene = chatscene1;
		regscene = regscene1;
		stage = stage1;
	}
	
	public void testmethod()
	{
        servscene.pane.setTop(createTitlebar());
        regscene.bpane.setTop(createTitlebar());
        chatscene.pane.setTop(createTitlebar());
        msgscene.header.setRight(createTitlebar());
	}
	
	public HBox createTitlebar()
	{
		Text close = new Text("x");
		Text min = new Text("_");
		close.setId("welcome");
		min.setId("welcome");
		close.setStyle("-fx-font-size: 30");
		min.setStyle("-fx-font-size: 30");
		HBox titlebar = new HBox();
		HBox.setMargin(close, new Insets(0,10,0,15));
		HBox.setMargin(min, new Insets(0,0,0,15));
		titlebar.setAlignment(Pos.CENTER_RIGHT);
        titlebar.getChildren().addAll(min, close);
        
       close.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.close();
			}

		});
       
       min.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.setIconified(true);
			}

		});
        
		return titlebar;
	}
	
	public void draggable() {		
		stage.initStyle(StageStyle.UNDECORATED);

		servscene.group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xvar= event.getSceneX();
				yvar= event.getSceneY();
			}
		});

		servscene.group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xvar);
				stage.setY(event.getScreenY() - yvar);
			}
		});
		
		chatscene.group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xvar= event.getSceneX();
				yvar= event.getSceneY();
			}
		});

		chatscene.group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xvar);
				stage.setY(event.getScreenY() - yvar);
			}
		});
		
		msgscene.group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xvar= event.getSceneX();
				yvar= event.getSceneY();
			}
		});

		msgscene.group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xvar);
				stage.setY(event.getScreenY() - yvar);
			}
		});
		
		regscene.group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xvar= event.getSceneX();
				yvar= event.getSceneY();
			}
		});

		regscene.group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xvar);
				stage.setY(event.getScreenY() - yvar);
			}
		});
	}

}
