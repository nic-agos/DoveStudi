package logic.model;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;
import logic.model.dao.*;

public class Group {
	
	private int id;
	
	private String name;
	
	private Account admin;
	
	private int numPartecipants;
	
	private List<Person> partecipants;
	
	
	public Group(String name, int numPartecipants) {
		
		this.name = name;
		
		this.numPartecipants = numPartecipants;
		
	}
	
	public Group(String name, Account admin, int numPartecipants, List<Person> partecipants) {
		
		this(name, numPartecipants);
		
		this.admin = admin;
		
		this.partecipants = partecipants;
		
	}
	
	public Group(GroupBean groupBean) throws SQLException {


		this(groupBean.getName(), groupBean.getNumPartecipants());
/*		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.admin = new Account(dao1.getAccount(groupBean.getAdmin()));
		
		PersonDAOImpl dao2 = new PersonDAOImpl();
		
		List<PersonBean> personBeans = dao2.getGroupPartecipants(groupBean);
		if(personBeans.isEmpty()) {
			for(PersonBean personBean : personBeans) {
				this.partecipants.add(new Person(personBean));
			}
		}
*/		
		this.id = groupBean.getId();
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
	
	public void setAdmin(Account admin) {
		this.admin = admin;
		
	}
	
	public Account getAdmin() {
		return this.admin;
		
	}
	
	public void setNumPartecipants(int numPartecipants) {
		this.numPartecipants = numPartecipants;
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
	}
	
	public void setPartecipant(List<Person> partecipants) {
		this.partecipants = partecipants;
		
	}
	
	public List<Person> getPartecipant() {
		return this.partecipants;
		
	}
}
