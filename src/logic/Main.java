package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.model.database.DBCreation;

public class Main extends Application{
	
	public static void main(String[] args) {
		DBCreation.createTables();
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		Parent root= FXMLLoader.load(getClass().getResource("view/fxml/Home.fxml"));
		stage.setTitle("DoveStudi");
		Scene scene= new Scene(root);
		stage.setScene(scene);		
		stage.show();		
	}
}
