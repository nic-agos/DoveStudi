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
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.*;
import logic.controller.ReservationController;
import logic.controller.RoomController;
import logic.model.*;
import logic.exception.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Search;
import logic.util.enumeration.Views;

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
	
	private static final String ERROR = "ERROR";
	
	private String search;
	
	private ObservableList<Room> searchResult;
	
	public SearchGC() {
		searchResult.clear();
	}
	
	public SearchGC(Search filter, String search) {
		
		this.search = search;
		
		switch(filter) {
		case CAP:
			searchByCap();break;
		case HOST:
			searchByUser();break;
		case ALL:
			searchRooms();break;
		case DATE:
			searchByDate();break;
		case SEATS:
			searchBySeats();break;
		default: 
			searchResult.clear();
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchBar.setText(search);
		if(searchResult!=null && !searchResult.isEmpty())
		{
			roomList.setItems(searchResult);
			roomList.setCellFactory(list -> new RoomCell());
		}
	}
	
	class RoomCell extends ListCell<Room>{
		@Override
		public void updateItem(Room item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				Label title = new Label("Room Name+ "+item.getName());
				Label description = new Label(item.getSpecification().getDescription());
				Label address = new Label ("Address: ********");
				Label cap = new Label("CAP: " + item.getSpecification().getCap());
				Label date = new Label("Date: " + item.getSpecification().getDate());
				Label start = new Label ("Start Time: " + item.getSpecification().getStartTime());
				Label end = new Label ("End Time: " + item.getSpecification().getEndTime());
				Label totalSeats = new Label ("Total Seats: "+ String.valueOf(item.getNumParticipants()));
				Label availableSeats = new Label ("Available Seats: "+ String.valueOf(item.getNumAvailableSeats()));

				description.setWrapText(true);
				
				Hyperlink hostLink = new Hyperlink();
				hostLink.setText(item.getOwner().getPerson().getUsername());
				hostLink.setOnAction(e-> {
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(hostLink.getText())));
				});
				
				ObservableList<Hyperlink> linkList = FXCollections.observableArrayList();
				
				for (Person p : item.getParticipants()) {
					Hyperlink link = new Hyperlink();
					link.setText(p.getUsername());
					link.setOnAction(e->{
						Stage stage = (Stage) main.getScene().getWindow();
						stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(link.getText())));
					});
					linkList.add(link);
				}
				
				ListView<Hyperlink> partecipants = new ListView<>();
				partecipants.setItems(linkList);
				partecipants.setOrientation(Orientation.HORIZONTAL);
				partecipants.setPrefHeight(25);
				partecipants.setMaxHeight(USE_PREF_SIZE);
				
				Button book = new Button("Book This Room");
				book.setOnAction(e->{
					RoomBean rBean = new RoomBean();
					rBean.setId(item.getId());
					AccountBean aBean = new AccountBean();
					aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
	
					ReservationController ctrl = ReservationController.getInstance();
					try{
						ctrl.makeReservation(rBean, aBean);
					}catch (DatabaseException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}catch (RoomException e2) {
						JOptionPane.showMessageDialog(null,e2.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}catch (AccountException e3) {
						JOptionPane.showMessageDialog(null,e3.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}catch (ReservationException e4) {
						JOptionPane.showMessageDialog(null,e4.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}
					
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.MYRESERVATIONS, null));
				});
				
				if(!Session.getSession().isLogged()) {
					book.setDisable(true);
				}
				
				v.getChildren().addAll(title,hostLink,description,address,cap,date,start,end,totalSeats,availableSeats,partecipants,book);
				setGraphic(v);			
			}
		}
	}
	
	@FXML
	public void allAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.ALL,"")));
	}
	@FXML
	public void capAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.CAP,searchBar.getText())));
	}
	@FXML
	public void dateAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.DATE,searchBar.getText())));
	}
	@FXML
	public void seatsAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.SEATS,searchBar.getText())));
	}
	@FXML
	public void hostAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC(Search.HOST,searchBar.getText())));
	}
	
	@FXML
	public void back() {
		if(Session.getSession().isLogged()) {
			Stage stage = (Stage) main.getScene().getWindow();
			stage.setScene(ViewSwitcher.switchTo(Views.MYACCOUNT,null));
		}
		
		else {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.HOME, null));
		}
		
	}
	
	public void searchByCap() {
		
		RoomSpecBean bean = new RoomSpecBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setCap(search);
		
		try {
			tempList = rContr.searchRoomByCap(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
				
			}else {
				roomsList=tempList;
			}
			
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchByUser() {
		
		PersonBean bean = new PersonBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setUsername(search);
		
		try {
			tempList = rContr.searchRoomByHost(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}	
			
			}else {
				roomsList=tempList;
			}
			
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchBySeats() {
		
		RoomBean bean = new RoomBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		if(search.equals("")) {
			
			bean.setNumAvailableSeats(1);
		
		}else {
			bean.setNumAvailableSeats(Integer.parseInt(search));
		}
	
		try {
			
			bean.validateSeats();
			
			tempList = rContr.searchRoomByAvailableSeats(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			
			}else {
				roomsList = tempList;
			}
			
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(RoomException re){
			JOptionPane.showMessageDialog(null,re.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchByDate() {
		
		RoomSpecBean bean = new RoomSpecBean();
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		bean.setDate(search);
		
		try {
			
			bean.validateDate();
			
			tempList = rContr.searchRoomByDate(bean);
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			else {
				roomsList=tempList;
			}
			
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(RoomException re) {
			JOptionPane.showMessageDialog(null,re.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchRooms() {
		RoomController rContr = RoomController.getInstance();
		
		List<Room> tempList = new ArrayList<>();
		List<Room> roomsList = new ArrayList<>();
		
		try {
			tempList = rContr.searchRooms();
			
			if(Session.getSession().getCurrUser() != null) {
				
				for(Room r : tempList) {
					
					if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0 ) {
						roomsList.add(r);
					}
				}
			}
			else {
				roomsList=tempList;
			}
			
			this.searchResult = FXCollections.observableArrayList(roomsList);
				
		}catch(DatabaseException de){
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne){
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR,JOptionPane.ERROR_MESSAGE);
		}
	}
}
