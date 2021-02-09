package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import logic.bean.*;
import logic.controller.*;
import logic.exception.*;
import logic.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.Session;
import logic.util.ViewSwitcher;

public class GroupBookRoomGC implements Initializable{
	
	@FXML
	private Button backBtn;
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Room> roomForGroupsList;
	
	private ObservableList<Room> roomsForGroup;
	
	public GroupBookRoomGC(String groupName) {
		getRoomsForGroup(groupName);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here right now*/
	}
	@FXML
	public void backAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
	
	private void getRoomsForGroup(String groupName) {
		
		GroupBean gBean = new GroupBean();
		Group group;
		GroupController gContr = GroupController.getInstance();
		RoomController rContr = RoomController.getInstance();
		
		gBean.setAdmin(Session.getSession().getCurrUser().getAccount().getCf());
		gBean.setName(groupName);
		
		try {
			group = gContr.getSpecificAdministeredGroup(gBean);
			
			RoomBean rBean = new RoomBean();
			rBean.setNumAvailableSeats(group.getNumParticipants());
			
			this.roomsForGroup = FXCollections.observableArrayList(rContr.searchRoomByAvailableSeats(rBean));
			
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne) {
			JOptionPane.showMessageDialog(null,ne.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
