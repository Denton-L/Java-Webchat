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

/**
 * Work with stage and scenes together.
 *
 * @author Jing Yi Xie
 * @version 2015-05-25
 */
public class StageModifier {
	
	/**
	 * The server page.
	 */
	private final ServScene servscene;
	/**
	 * The message page.
	 */
	private final MsgScene msgscene;
	/**
	 * The login page.
	 */
	private final LoginScene loginscene;
	/**
	 * The registration page.
	 */
	private final RegScene regscene;
	/**
	 * The top level container of the interface.
	 */
	private final Stage stage;
	/**
	 * The registration scene.
	 */
	private final Scene reg;
	/**
	 * The server scene.
	 */
	private final Scene serv;
	/**
	 * The login scene.
	 */
	private final Scene login;
	private final Scene msg;
	/**
	 * The variables to deal with dragging the window.
	 */
	private double xvar, yvar;
	
	/**
	 * 
	 * @param serv1 The server scene.
	 * @param login1 The login scene.
	 * @param reg1 The registration scene.
	 * @param servscene1 The server page.
	 * @param msgscene1 The message page.
	 * @param chatscene1 The login page.
	 * @param regscene1 The registration page.
	 * @param stage1 The top level container.
	 */
	public StageModifier(Scene serv1, Scene login1, Scene reg1, Scene msg1, ServScene servscene1, MsgScene msgscene1,
			LoginScene loginscene1, RegScene regscene1, Stage stage1) {

		this.reg = reg1;
		this.serv = serv1;
		this.login = login1;
		this.msg = msg1;
		this.servscene = servscene1;
		this.msgscene = msgscene1;
		this.loginscene = loginscene1;
		this.regscene = regscene1;
		this.stage = stage1;
	}
	
	/**
	 * Set the titlebar on all scenes.
	 */
	public void testmethod() {
		this.servscene.getPane().setTop(createTitlebar());
		this.regscene.getBpane().setTop(createTitlebar());
		this.loginscene.getPane().setTop(createTitlebar());
		this.msgscene.getHeader().setRight(createTitlebar());
	}
	
	/**
	 * Create titlebar to be placed.
	 */
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
				System.exit(0);
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
				else if (stage.getScene() == msg)
				stage.setScene(login);
			}

		});
        
		return titlebar;
	}
	
	/**
	 * Set all scenes as draggable.
	 */
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
		
		this.loginscene.getGroup().setOnMousePressed(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						StageModifier.this.xvar = event.getSceneX();
						StageModifier.this.yvar = event.getSceneY();
					}
				});
		
		this.loginscene.getGroup().setOnMouseDragged(
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
