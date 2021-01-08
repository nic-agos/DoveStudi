package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import logic.model.dao.*;
import logic.model.*;
import logic.bean.*;

public class GenericTester {

	@Test
	public void testCreateAccount() throws SQLException {
		
		boolean bool;
		AccountDAOImpl dao1 = new AccountDAOImpl();
		PersonDAOImpl dao2 = new PersonDAOImpl();
		
		AccountBean accountInput = new AccountBean();
	
		bool = accountInput.setCf("GSTNCL99C23H501K");
		if (!bool) {
			System.out.println("Errore nell'inserimento del codice fiscale");
		}
	
		bool = accountInput.setName("Niccolò");
		if (!bool) {
			System.out.println("Errore nell'inserimento del nome");
		}
	
		bool = accountInput.setSurname("Agostinelli");
		if (!bool) {
			System.out.println("Errore nell'inserimento del cognome");
		}
	
		bool = accountInput.setEmail("nik.agos@gmail.com");
		if (!bool) {
			System.out.println("Errore nell'inserimento della mail");
			}		
	
		bool = accountInput.setPassword("password");
		if(!bool) {
			System.out.println("Errore nell'inserimento della password");
		}
	
		accountInput.setDateBirth("1999-03-23");
		accountInput.setNumberToken(0);
	
		int resQuery = dao1.createAccount(accountInput);
		System.out.println(resQuery);
		if(resQuery == 1) {
		
			PersonBean personInput = new PersonBean();
			personInput.setUsername("nicco2303");
			personInput.setStudyGrade("university");
			personInput.setSchool("Uniroma2");
			personInput.setAccount("GSTNCL99C23H501K");
			personInput.setHostRating(0);
			personInput.setGuestRating(0);
		
			int personId = dao2.createPerson(personInput);
			if(personId == 0) {
				System.out.println("Errore nella creazione della person");
			}
			else {
				personInput.setId(personId);
				Account account1 = new Account(accountInput);
				Person person1 = new Person(personInput);
				account1.setPerson(person1);
				System.out.println(account1.getCf() + " " + account1.getName() + " " + account1.getSurname() + " " + account1.getEmail() +
					" " + account1.getPassword() + " " + account1.getDateBirth() + " " + account1.getNumberToken() + "\n");
				
				System.out.println(account1.getPerson().getId());
			}
		
		}else {
			System.out.println("errore nella creazione dell'account");
		}	
	}
		
}
