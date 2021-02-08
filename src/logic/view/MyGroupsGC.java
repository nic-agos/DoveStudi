package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import logic.model.Group;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class MyGroupsGC implements Initializable{
	
	@FXML
	private Button createBtn;
	@FXML 
	private BorderPane main;
	@FXML
	private ListView<Group> groupsList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	/***nothing to do here for now*/	
	}
	
	@FXML
	public void createGroup() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.GROUPCREATION, null));
	}
	
}
