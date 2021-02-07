package logic.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class NavbarGC {
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
	public void goToRooms() {
		ViewSwitcher.switchTo(Views.MYROOMS, new HomeGC());
	}
	@FXML
	public void goToReservations() {
		ViewSwitcher.switchTo(Views.MYRESERVATIONS, new HomeGC());
	}
	@FXML
	public void goToGroups() {
		ViewSwitcher.switchTo(Views.MYGROUPS, new HomeGC());
	}
	@FXML
	public void goToPostRoom() {
		ViewSwitcher.switchTo(Views.POSTROOM, new HomeGC());
	}
	@FXML
	public void goToReviews() {
		ViewSwitcher.switchTo(Views.MYREVIEWS, new HomeGC());
	}
	@FXML
	public void goToSearch() {
		ViewSwitcher.switchTo(Views.ROOMSEARCH, new HomeGC());
	}
	@FXML
	public void doLogOut(){
		Session.getSession().setLogged(false);
		ViewSwitcher.switchTo(Views.HOME, new HomeGC());
	}
	
	
	
}
