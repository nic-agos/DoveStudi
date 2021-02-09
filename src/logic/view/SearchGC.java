package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.PersonBean;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;
import logic.controller.RoomController;
import logic.model.Reservation;
import logic.model.Room;
import logic.exception.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Search;

public class SearchGC implements Initializable{
	@FXML
	private BorderPane main;
	@FXML
	private Button capBtn;
	@FXML
	private Button hostBtn;
	@FXML
	private Button dateBtn;
	@FXML
	private Button seatsBtn;
	@FXML
	private Button allBtn;
	@FXML
	private Button back;
	@FXML
	private ListView<Room> roomList;
	@FXML
	private TextField searchBar;
	
	private static final String ERROR = "Error";
	
	private ObservableList<Room> searchResult;
	
	public void SearchGC(Search filter) {
		switch(filter) {
		case CAP:
			searchByCap();break;
		case HOST:
			searchByUser();break;
		case ALL:
			break;
		case DATE:
			searchByDate();break;
		case SEATS:
			searchBySeats();break;
		default: 
			searchResult=null;
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
	
	public void searchByCap() {
		
		RoomSpecBean bean = new RoomSpecBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setCap(searchBar.getText());
		
		try {
			tempList = rContr.searchRoomByCap(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
	
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchByUser() {
		
		PersonBean bean = new PersonBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setUsername(searchBar.getText());
		
		try {
			tempList = rContr.searchRoomByHost(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
	
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchBySeats() {
		
		RoomBean bean = new RoomBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setNumAvailableSeats(Integer.parseInt(searchBar.getText()));
		
		try {
			tempList = rContr.searchRoomByAvailableSeats(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
	
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchByDate() {
		
		RoomSpecBean bean = new RoomSpecBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setDate(searchBar.getText());
		
		try {
			tempList = rContr.searchRoomByDate(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
	
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
