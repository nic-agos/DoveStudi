package logic.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ViewSwitcher;

public class AddGroupMemberGC {
	
	@FXML
	private BorderPane main;
	@FXML 
	private Button backBtn;
	@FXML
	private Button addBtn;
	@FXML
	private TextField usernameLbl;
	
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
