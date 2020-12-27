package logic.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.scene.Node;

public class HomeGController {
	
	public void mouseClick(MouseEvent mouseEvent) throws Exception {
		
		Button button = (Button) mouseEvent.getSource();
		String buttonText = button.getText();
		
		Node source = (Node) mouseEvent.getSource();
		Window currentWindow = source.getScene().getWindow();
		
		Parent root; Scene scene; Stage stage;
		
		switch (buttonText){
			case("Log In"):
				root= FXMLLoader.load(getClass().getResource("LogIn.fxml"));
				stage=new Stage();
				scene= new Scene(root);
				stage.setScene(scene);
				currentWindow.hide();
				stage.setTitle("Log In");
				stage.show();
				break;
			case("Groups"):
				System.out.println("Groups View");
				break;
			case("Account"):
				System.out.println("Account View");
				break;
		}
	}
}
