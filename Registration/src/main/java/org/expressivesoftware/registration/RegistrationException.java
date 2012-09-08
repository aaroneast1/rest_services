package org.expressivesoftware.registration;

public class RegistrationException extends RuntimeException {

	private static final long serialVersionUID = -6293328871316330200L;

	public RegistrationException() {
		super();
	}

	public RegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrationException(String message) {
		super(message);
	}

	public RegistrationException(Throwable cause) {
		super(cause);
	}
}
