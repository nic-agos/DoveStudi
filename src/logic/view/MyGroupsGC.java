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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class MyGroupsGC implements Initializable{
	
	@FXML
	private Button createBtn;
	@FXML 
	private BorderPane main;
	@FXML
	private ListView<Group> groupsList;
	
	private ObservableList<Group> groups;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	/***nothing to do here for now*/	
	}
	
	@FXML
	public void createGroup() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.GROUPCREATION, null));
	}
	

	private void getGroups() {
		PersonBean pBean = new PersonBean();
		AccountBean aBean = new AccountBean();
		List<Group> partGroups = new ArrayList<>();
		List<Group> adminGroups = new ArrayList<>();
		List<Group> groupsList = new ArrayList<>();
		
		GroupController gContr = GroupController.getInstance();
		
		pBean.setId(Session.getSession().getCurrUser().getId());
		aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			partGroups = gContr.getParticipatingGroups(pBean);
			adminGroups = gContr.getAdministeredGroups(aBean);

			groupsList.addAll(adminGroups);
			groupsList.addAll(partGroups);
			
			this.groups = FXCollections.observableArrayList(groupsList);
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
