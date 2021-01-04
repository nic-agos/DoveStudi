package logic.bean;


public class GroupBean {
	
	private int id;
	
	private String name;
	
	private String admin;
	
	private int numPartecipants;
	
	private int partecipant;
	
	
	public GroupBean() {
		
	}
	
	public GroupBean(String name, String admin, int numPartecipants, int partecipant) {
		
		this.name = name;
		
		this.admin = admin;
		
		this.numPartecipants = numPartecipants;
		
		this.partecipant = partecipant;
	}
	
	public GroupBean(int id, String name, String admin, int numPartecipants, int partecipant) {
		
		this(name, admin, numPartecipants, partecipant);
		
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
		
	}
	
	public int getId() {
		return this.id;
		
	}
	
	public boolean setName(String name) {
		if (name.length() <= 45) {
			this.name = name;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setAdmin(String admin) {
		this.admin = admin;	
		
	}
	
	public String getAdmin() {
		return this.admin;
		
	}
	
	public void setNumPartecipants(String numPartecipants) {
		this.numPartecipants = Integer.parseInt(numPartecipants);
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
	}
	
	public void setPartecipant(int partecipant) {
		this.partecipant = partecipant;
		
	}
	
	public int getPartecipant() {
		return this.partecipant;
		
	}
}
