package logic.exception;

public class TriggerExceptions {
	
	public void triggerRegistrationAccountException(String message) throws AccountException {
		throw new AccountException(message);
		
	}
	
	public void triggerRegistrationPersonException(String message) throws PersonException {
		throw new PersonException(message);
		
	}
	
}
