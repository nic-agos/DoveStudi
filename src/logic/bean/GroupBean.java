package logic.bean;

import logic.exception.*;

public class GroupBean {
	
	private int id;
	
	private String name;
	
	private String admin;
	
	private int numParticipants;
	
	private int participant;
	
	
	public GroupBean() {
		
	}
	
	public GroupBean(String name, String admin, int numPartecipants, int partecipant) {
		
		this.name = name;
		
		this.admin = admin;
		
		this.numParticipants = numPartecipants;
		
		this.participant = partecipant;
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
	
	public void setName(String name) {
		this.name = name;
		
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
	
	public void setNumParticipants(int numParticipants) {
		this.numParticipants = numParticipants;
		
	}
	
	public int getNumParticipants() {
		return this.numParticipants;
		
	}
	
	public void setParticipant(int participant) {
		this.participant = participant;
		
	}
	
	public int getParticipant() {
		return this.participant;
		
	}
	
	public void validate() throws GroupException {
		
		String errors = "";
		
		if(this.name.length() > 45 || this.name.isBlank()) {
			errors = errors + "Invalid Group Name";
		
		}
		
		if(!errors.isEmpty()) {
			
			throw new GroupException(errors);
		}
	}
}
