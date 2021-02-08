package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ViewSwitcher;

public class GroupBookRoomGC implements Initializable{
	
	@FXML
	private Button backBtn;
	@FXML
	private BorderPane main;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here right now*/
	}
	@FXML
	public void backAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
