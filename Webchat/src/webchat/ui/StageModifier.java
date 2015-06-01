package webchat.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageModifier {
	private final ServScene servscene;
	private final MsgScene msgscene;
	private final ChatScene chatscene;
	private final RegScene regscene;
	private final Stage stage;
	private final Scene reg;
	private final Scene serv;
	private final Scene login;
	private double xvar, yvar;
	
	public StageModifier(Scene serv1, Scene login1, Scene reg1, ServScene servscene1, MsgScene msgscene1,
			ChatScene chatscene1, RegScene regscene1, Stage stage1) {
		this.reg = reg1;
		this.serv = serv1;
		this.login = login1;
		this.servscene = servscene1;
		this.msgscene = msgscene1;
		this.chatscene = chatscene1;
		this.regscene = regscene1;
		this.stage = stage1;
	}
	
	public void testmethod() {
		this.servscene.getPane().setTop(createTitlebar());
		this.regscene.getBpane().setTop(createTitlebar());
		this.chatscene.getPane().setTop(createTitlebar());
		this.msgscene.getHeader().setRight(createTitlebar());
	}
	
	public HBox createTitlebar()
	{
		Text back = new Text("<");
		Text close = new Text("x");
		Text min = new Text("_");
		close.setId("welcome");
		min.setId("welcome");
		back.setId("welcome");
		close.setStyle("-fx-font-size: 30");
		min.setStyle("-fx-font-size: 30");
		back.setStyle("-fx-font-size: 35");
		HBox titlebar = new HBox();
		HBox.setMargin(close, new Insets(0,10,0,15));
		HBox.setMargin(min, new Insets(0,0,0,15));
		HBox.setMargin(back, new Insets(7,0,0,12));
		titlebar.setAlignment(Pos.CENTER_RIGHT);
		titlebar.getChildren().addAll(back,min,close);

        
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
       
       back.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (stage.getScene() == login)
				stage.setScene(serv);
				else if (stage.getScene() == reg)
				stage.setScene(login);
			}

		});
        
		return titlebar;
	}
	
	public void draggable() {
		this.stage.initStyle(StageStyle.UNDECORATED);
		
		this.servscene.getGroup().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.xvar = event.getSceneX();
						StageModifier.this.yvar = event.getSceneY();
					}
				});
		
		this.servscene.getGroup().setOnMouseDragged(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.stage.setX(event.getScreenX()
								- StageModifier.this.xvar);
						StageModifier.this.stage.setY(event.getScreenY()
								- StageModifier.this.yvar);
					}
				});
		
		this.chatscene.getGroup().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.xvar = event.getSceneX();
						StageModifier.this.yvar = event.getSceneY();
					}
				});
		
		this.chatscene.getGroup().setOnMouseDragged(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.stage.setX(event.getScreenX()
								- StageModifier.this.xvar);
						StageModifier.this.stage.setY(event.getScreenY()
								- StageModifier.this.yvar);
					}
				});
		
		this.msgscene.getGroup().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.xvar = event.getSceneX();
						StageModifier.this.yvar = event.getSceneY();
					}
				});
		
		this.msgscene.getGroup().setOnMouseDragged(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.stage.setX(event.getScreenX()
								- StageModifier.this.xvar);
						StageModifier.this.stage.setY(event.getScreenY()
								- StageModifier.this.yvar);
					}
				});
		
		this.regscene.getGroup().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.xvar = event.getSceneX();
						StageModifier.this.yvar = event.getSceneY();
					}
				});
		
		this.regscene.getGroup().setOnMouseDragged(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.stage.setX(event.getScreenX()
								- StageModifier.this.xvar);
						StageModifier.this.stage.setY(event.getScreenY()
								- StageModifier.this.yvar);
					}
				});
	}
	
}
