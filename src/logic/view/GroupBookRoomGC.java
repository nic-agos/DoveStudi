package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class GroupBookRoomGC implements Initializable{
	
	@FXML
	private Button backBtn;
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Room> roomForGroupsList;
	
	private static final String ERROR = "ERROR";
	
	private String gName;
	
	private ObservableList<Room> roomsForGroup;
	
	public GroupBookRoomGC(String groupName) {
		this.gName=groupName;
		getRoomsForGroup(gName);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roomForGroupsList.setItems(roomsForGroup);
		roomForGroupsList.setCellFactory(list -> new RoomCell());
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
				partecipants.setPrefHeight(30);
				partecipants.setMaxHeight(USE_PREF_SIZE);
				
				Button book = new Button("Book This Room");
				book.setOnAction(e->{
					RoomBean rBean = new RoomBean();
					rBean.setId(item.getId());
					GroupBean gBean = new GroupBean();
					gBean.setAdmin(Session.getSession().getCurrUser().getAccount().getCf());
					gBean.setName(gName);
					GroupController ctrl = GroupController.getInstance();
					
					try{
						ctrl.bookRoomGroup(gBean, rBean);
					
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
				
				v.getChildren().addAll(title,description,address,cap,date,start,end,totalSeats,availableSeats,partecipants,book);
				v.setAlignment(Pos.CENTER);
				v.setSpacing(5);
				v.setMaxWidth(1000);
				setGraphic(v);			
			}
		}
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
		
		List<Room> roomsList = new ArrayList<>();
		List<Room> tempList = new ArrayList<>();
		
		gBean.setAdmin(Session.getSession().getCurrUser().getAccount().getCf());
		gBean.setName(groupName);
		
		try {
			group = gContr.getSpecificAdministeredGroup(gBean);
			
			RoomBean rBean = new RoomBean();
			rBean.setNumAvailableSeats(group.getNumParticipants());
			
			tempList = rContr.searchRoomByAvailableSeats(rBean);
			
			for(Room r : tempList) {
				if(r.getOwner().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf()) != 0) {
					roomsList.add(r);
				}
			}
			this.roomsForGroup = FXCollections.observableArrayList(roomsList);
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}catch(NotFoundException ne) {
			JOptionPane.showMessageDialog(null,ne.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
