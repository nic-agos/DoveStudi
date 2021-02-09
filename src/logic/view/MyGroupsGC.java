package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import logic.bean.*;
import logic.controller.GroupController;
import logic.exception.DatabaseException;
import logic.model.Group;
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
	
	private List<Group> adminGroups = new ArrayList<>();
	private List<Group> partGroups = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	/***nothing to do here for now*/	
	}
	
	@FXML
	public void createGroup() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.GROUPCREATION, null));
	}
	
	public void getAdminGroups() {
		AccountBean aBean = new AccountBean();
		
		GroupController gContr = GroupController.getInstance();
		
		aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			
			this.adminGroups = gContr.getAdministeredGroups(aBean);
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void getPartGroups() {
		PersonBean pBean = new PersonBean();
		
		GroupController gContr = GroupController.getInstance();
		pBean.setId(Session.getSession().getCurrUser().getId());
		
		try {
			
			this.partGroups = gContr.getParticipatingGroups(pBean);
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
