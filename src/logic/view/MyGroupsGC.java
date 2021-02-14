package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import logic.bean.*;
import logic.controller.GroupController;
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

/*Linked FXML file: MyGroups.fxml*/
public class MyGroupsGC implements Initializable{
	
	@FXML
	private Button createBtn;
	@FXML 
	private BorderPane main;
	@FXML
	private ListView<Group> groupsList;
	private static final String ERROR = "ERROR";
	private ObservableList<Group> groups;	
	
	@Override /*Initialize the ListView*/
	public void initialize(URL location, ResourceBundle resources) {
		setGroups();
		
		if(!groups.isEmpty()) {
			groupsList.setFocusTraversable(false);
			groupsList.setItems(groups);
			groupsList.setCellFactory(list -> new GroupCell());
		}
		
	}
	
	//Cell Style of the ListView
	class GroupCell extends ListCell<Group>{
		@Override
		public void updateItem(Group item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				//Group informations
				Label name = new Label(item.getName());
				Label admin = new Label("Admin: "); 
				Label numPartecipants = new Label("Partecipants: "+ String.valueOf(item.getNumParticipants()));
				
					//List of Link, group partecipants
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
				
				//Admin Link
				Hyperlink adminLink = new Hyperlink(item.getAdmin().getPerson().getUsername());
				adminLink.setOnAction(e-> {
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(adminLink.getText())));
				});
				//Buttons and their actions
				Button book = new Button("Book Room");
				Button add = new Button("Add Member");
				Button delete = new Button("Delete Group");
				Button leave = new Button("Leave Group");
				
				book.setOnAction(e1->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.GROUPBOOKING, new GroupBookRoomGC(item.getName())));
				});
				
				add.setOnAction(e2-> {
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.ADDGROUPMEMBER, new AddGroupMemberGC(item.getName())));
				});
				
				delete.setOnAction(e3->{
					GroupBean bean = new GroupBean();
					bean.setAdmin(item.getAdmin().getCf());
					bean.setName(item.getName());
					GroupController ctrl = GroupController.getInstance();
					try {
						ctrl.deleteGroup(bean);
					
					}catch(DatabaseException ex1) {
						JOptionPane.showMessageDialog(null,ex1.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}
					
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.MYGROUPS,null));
				});
				
				leave.setOnAction(e4->{
					GroupBean bean = new GroupBean();
					bean.setAdmin(item.getAdmin().getCf());
					bean.setName(item.getName());
					bean.setParticipant(Session.getSession().getCurrUser().getId());
					GroupController ctrl = GroupController.getInstance();
					try{
						ctrl.leaveGroup(bean);
						
					}catch(DatabaseException ex1) {
						JOptionPane.showMessageDialog(null,ex1.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}catch(AccountException ex2) {
						JOptionPane.showMessageDialog(null,ex2.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}
					
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.MYGROUPS,null));
				});
				//If the current user is the admin, add the book, delete and add buttons 
				if(Session.getSession().getCurrUser().getUsername().compareTo(item.getAdmin().getPerson().getUsername())==0) {
					v.getChildren().addAll(name, admin, adminLink, numPartecipants, partecipants,add,delete,book);
				}
				//Else add the leave button
				else {
					v.getChildren().addAll(name, admin, adminLink, numPartecipants, partecipants,leave);
				}
				v.setAlignment(Pos.CENTER);
				v.setSpacing(5);
				v.setMaxWidth(1000);
				setGraphic(v);				
			}
		}
	}
	@FXML /*Action associated to the createBtn*/
	public void createGroup() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.GROUPCREATION, null));
	}
	
	/*Get all the groups of an account and set an ObservableList*/
	private void setGroups() {
		PersonBean pBean = new PersonBean();
		AccountBean aBean = new AccountBean();
		List<Group> partGroups = new ArrayList<>();
		List<Group> adminGroups = new ArrayList<>();
		List<Group> gList = new ArrayList<>();
		List<Group> temp = new ArrayList<>();
		GroupController gContr = GroupController.getInstance();
		
		pBean.setId(Session.getSession().getCurrUser().getId());
		aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			temp = gContr.getParticipatingGroups(pBean);
			adminGroups = gContr.getAdministeredGroups(aBean);
			for(Group g : temp) {
				if(g.getAdmin().getCf().compareTo(Session.getSession().getCurrUser().getAccount().getCf())!=0) {
					partGroups.add(g);
				}
			}
			gList.addAll(adminGroups);
			gList.addAll(partGroups);
			
			this.groups = FXCollections.observableArrayList(gList);
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
