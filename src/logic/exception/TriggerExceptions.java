package logic.exception;

import logic.exception.RegistrationException;

public class TriggerExceptions {
	
	public void triggerRegistrationException(String message) throws RegistrationException {
		throw new RegistrationException(message);
		
	}
}
