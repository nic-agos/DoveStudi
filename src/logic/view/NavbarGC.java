package logic.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Search;
import logic.util.enumeration.Views;

/*Linked FXML file: Navbar.fxml
 * This is the graphic controller for the left navbar
 * This is added to the scene only in some cases (look at ViewSwitcher in package utils)
 */
public class NavbarGC {
	@FXML
	private Button myAccountBtn;
	@FXML
	private Button myRoomsBtn;
	@FXML
	private Button myResBtn;
	@FXML
	private Button myGroupsBtn;
	@FXML
	private Button myReviewsBtn;
	@FXML
	private Button postRoomBtn;
	@FXML
	private Button searchRoomBtn;
	@FXML
	private Button logOutBtn;
	@FXML
	private BorderPane main;
	
	//All the methods are Button events and they are used to navigate

	@FXML
	public void goToAccount() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYACCOUNT, null));
	}
	@FXML
	public void goToRooms() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYROOMS, null));
	}
	@FXML
	public void goToReservations() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYRESERVATIONS, null));
	}
	@FXML
	public void goToGroups() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYGROUPS, null));
	}
	@FXML
	public void goToPostRoom() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.POSTROOM, null));
	}
	@FXML
	public void goToReviews() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYREVIEWS, null));
	}
	@FXML
	public void goToSearch() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.ALL,"")));
	}
	@FXML
	public void doLogOut(){
		Stage stage = (Stage) main.getScene().getWindow();
		Session.getSession().setLogged(false);
		stage.setScene(ViewSwitcher.switchTo(Views.HOME, null));
	}
	
	
	
}
