package logic.exception;

public class TriggerExceptions {
	
	public void triggerRegistrationAccountException(String message) throws AccountException {
		throw new AccountException(message);
		
	}
	
}
