package logic.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LogInGC {
	
		//This method is an Event Listner for the buttons in the Log In Page
		public void mouseClick(MouseEvent mouseEvent) throws Exception {
		
		Button button = (Button) mouseEvent.getSource();
		String buttonText = button.getText();
		
		Node source = (Node) mouseEvent.getSource();
		Window currentWindow = source.getScene().getWindow();
		
		Parent root;
		Scene scene;
		Stage stage;
		
		switch (buttonText){
			case("Back"):
				
				//Recreate the Home Scene closing the Log In one
				root= FXMLLoader.load(getClass().getResource("Home.fxml"));
				stage=new Stage();
				scene= new Scene(root);
				stage.setScene(scene);
				currentWindow.hide();
				stage.setTitle("DoveStudi");
				stage.show();
				break;
				
			case ("Enter"):
				
				//If Log In is succesful, redirect to the Logged Home
				System.out.println("You logged in");
				root= FXMLLoader.load(getClass().getResource("LoggedHome.fxml"));
				stage=new Stage();
				scene= new Scene(root);
				stage.setScene(scene);
				currentWindow.hide();
				stage.setTitle("DoveStudi");
				stage.show();
				break;
			
			case ("I don't have an account"):
				System.out.println("me sa che fai come Emily");
				break;
			default: System.out.println("default");

		}
		
	}
}