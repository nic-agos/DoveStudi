package logic.view;

import javafx.application.Application;
/*import javafx.event.ActionEvent;
import javafx.event.EventHandler;*/
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class TopBar extends Application {
	
	//RIMUOVI IL MAIN
	public static void main (String args[]) {
		launch(args);
	}
	//RIMUOVI IL MAIN
	
	protected BorderPane borderPane;

	private HBox hBoxLogin;

	private Button btnLogin = new Button("Login");
	private Button btnSignIn = new Button("Sign In");
	private Button btnGroups = new Button("Groups");
	//private Button btnUserProfile = new Button("User Profile");

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("DoveStudi");

		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));

		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("DoveStudi");
		title.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);
		hBoxLogin = new HBox(10);
		hBoxLogin.setAlignment(Pos.CENTER_RIGHT);
		hBoxLogin.getChildren().addAll(btnLogin, btnSignIn, btnGroups);

		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle, hBoxLogin);

		borderPane.setTop(hBoxTop);

		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
}
