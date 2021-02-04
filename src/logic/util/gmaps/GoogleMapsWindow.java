package logic.util.gmaps;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
/*
 * HOW TO USE THIS CLASS
 * new GoogleMapsWindow(address) Use the constructor giving it an address, 
 * then create a new scene and use the method createBorderPane as argument
 */
public class GoogleMapsWindow {

	private BorderPane borderPane;
	private WebView linkToMaps;
	private String rawAddress;
	
	public GoogleMapsWindow(String address) {
		
		this.rawAddress = address;
		GoogleMapsResearch converter = new GoogleMapsResearch();
		
		String searchAddress = converter.addressConverter(rawAddress);
		
		borderPane = new BorderPane();
		linkToMaps = new WebView();
		linkToMaps.getEngine().load(searchAddress);
		
	}
	
	public BorderPane createBorderPane() {
		
		borderPane.setCenter(linkToMaps);
		
		return borderPane;
	}
		
}
