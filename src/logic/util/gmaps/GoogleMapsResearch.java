package logic.util.gmaps;

public class GoogleMapsResearch {
	
	private StringBuilder clearAddress = new StringBuilder();
	
	public GoogleMapsResearch() {
		/*nothing to do here*/
	}
	
	public String addressConverter(String rawAddress) {
		String prefix = "https://www.google.com/maps/search/?api=1&query=";
		
		for(int i=0; i < rawAddress.length(); i++) {
			char c = rawAddress.charAt(i);
			if (c == ' ') {
				clearAddress.append("+");
			}else {
				clearAddress.append(c);
			}
		}
		return prefix+clearAddress;
	}
	
}
