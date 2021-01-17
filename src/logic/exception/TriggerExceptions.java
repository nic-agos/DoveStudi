package logic.exception;

public class TriggerExceptions {
	
	public void triggerRegistrationAccountException(String message) throws RegistrationAccountException {
		throw new RegistrationAccountException(message);
		
	}
	
	public void triggerRegistrationPersonException(String message) throws RegistrationPersonException {
		throw new RegistrationPersonException(message);
		
	}
	
}
