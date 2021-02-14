package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import logic.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.enumeration.Search;
import logic.util.enumeration.Views;

/*Linked FXML file: Home.fxml */
public class HomeGC implements Initializable {
	
	@FXML
	private Button postBtn;
	@FXML
	private Button lookBtn;
	@FXML
	private Button logInBtn;
	@FXML
	private BorderPane main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* nothing to do here*/
	}
	
	@FXML /*Action associated to the lookBtn*/
	public void lookAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.ALL,"")));
	}
	@FXML /*Action associated to the loginBtn*/
	public void logInAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.LOGIN, null));
	}
}
