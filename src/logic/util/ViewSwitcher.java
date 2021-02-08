package logic.util;
import logic.util.enumeration.Views;
import logic.view.NavbarGC;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class ViewSwitcher {
	private static final String PATH="../view/fxml/";
	
	private static BorderPane navbar = null;

	
	private ViewSwitcher() {/*Nothing to do here*/}
	
	public static FXMLLoader loadFXML(Views view) {
		switch(view) {
		
		case LOGIN:	
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "LogIn.fxml"));
		case MYACCOUNT:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyAccount.fxml"));
		case MYGROUPS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyGroups.fxml"));
		case MYRESERVATIONS:
			return new FXMLLoader(ViewSwitcher.class.getResource(PATH + "MyReservation.fxml"));
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
	
	private static BorderPane getNavbar() throws IOException {
		if (navbar == null) {
			FXMLLoader loader = loadFXML(Views.NAVBAR);
			NavbarGC navCtrl = new NavbarGC();
			loader.setController(navCtrl);
			navbar = loader.load();
		}
		return navbar;
	}
	
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
			if(nextView.equals(Views.MYACCOUNT)||nextView.equals(Views.MYGROUPS)||nextView.equals(Views.MYROOMS)||nextView.equals(Views.MYREVIEWS)){
				pane.setLeft(ViewSwitcher.getNavbar());
			}
			return new Scene(pane);
		}
	}
	catch (IOException e)
	{
		e.printStackTrace();
		return new Scene(create404Page(nextView.toString().toLowerCase()));
	
	}
}
	public static Scene back(){
		return ViewSwitcher.switchTo(Session.getSession().getPrevView(), null);
	}
	
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
