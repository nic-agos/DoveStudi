package logic.util;
import logic.util.enumeration.Views;
import logic.view.NavbarGC;
import logic.view.SearchGC;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
/*
 * 
 * This class has only static methods and it's used to switch between scene
 * This class only returns scenes generated from FXML files, not full stages
 * FXML files package -> logic.view.fxml 
 * 
 * 
 * */


public class ViewSwitcher {
	private static final String PATH="../view/fxml/";
	
	private static BorderPane navbar = null;

	
	private ViewSwitcher() {/*Nothing to do here*/}
	
/*	 This method is used to load the FXML file
	 when the file is loaded, the associated controller is created, the constructor is called, the @FXML fields are populated
	 and then the initialize() method
*/
	public static FXMLLoader loadFXML(Views view) {
		switch(view) {
		
		case LOGIN:	
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "LogIn.fxml"));
		case MYACCOUNT:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyAccount.fxml"));
		case MYGROUPS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyGroups.fxml"));
		case MYRESERVATIONS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyReservations.fxml"));
		case MYREVIEWS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyReviews.fxml"));
		case MYROOMS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyRooms.fxml"));
		case ROOMSEARCH:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "Search.fxml"));
		case OTHERACCOUNT:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "OtherAccount.fxml"));
		case POSTROOM:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "PostRoom.fxml"));
		case REVIEWFORM:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "ReviewForm.fxml"));
		case GROUPBOOKING:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "GroupBookRoom.fxml"));
		case GROUPCREATION:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "CreateGroup.fxml"));
		case NAVBAR:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "Navbar.fxml"));
		case REGISTRATION:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "Registration.fxml"));		
		case ADDGROUPMEMBER:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "AddGroupMember.fxml"));	
		default: /*case HOME*/
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "Home.fxml"));
		}
	}
	/*This method load and return the navbar*/
	private static BorderPane getNavbar() throws IOException {
		if (navbar == null) {
			FXMLLoader loader = loadFXML(Views.NAVBAR);
			NavbarGC navCtrl = new NavbarGC();
			loader.setController(navCtrl);
			navbar = loader.load();
		}
		return navbar;
	}
	/*This method return the nextView scene with the default controller specified in the FXML file if the second field is null*/
	public static Scene switchTo (Views nextView, Initializable controller){
		Session.getSession().setCurrView(nextView);
	try {
		if (nextView.equals(Views.LOGIN)) {
			Session.getSession().setCurrUser(null);
			return new Scene(loadFXML(nextView).load());
		}
		else {
			FXMLLoader loader = loadFXML(nextView);
			if (controller != null)
				loader.setController(controller);
			BorderPane pane = loader.load();
			if(nextView.equals(Views.MYACCOUNT)||nextView.equals(Views.MYGROUPS)||nextView.equals(Views.MYROOMS)||nextView.equals(Views.MYREVIEWS)||nextView.equals(Views.MYRESERVATIONS)){
				pane.setLeft(ViewSwitcher.getNavbar());
			}
			return new Scene(pane);
		}
	}
	catch (IOException e)
	{
		return new Scene(create404Page(nextView.toString().toLowerCase()));
	
	}
}
	/*Load the previous scene*/
	public static Scene back(){
		if(Session.getSession().getPrevView().equals(Views.ROOMSEARCH))
			return (ViewSwitcher.switchTo(Views.ROOMSEARCH, new SearchGC()));
		else
			return ViewSwitcher.switchTo(Session.getSession().getPrevView(), null);
	}
	
	/*404 Page nothing special*/
	private static VBox create404Page(String view) {
		
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20);
		box.setPrefSize(900, 600);
		
		Label err = new Label("404 - Page Not Found");
		Label message = new Label("Unable to load " + view +".fxml file");
		
		box.getChildren().addAll(err,message);
		return box;
	}
}
