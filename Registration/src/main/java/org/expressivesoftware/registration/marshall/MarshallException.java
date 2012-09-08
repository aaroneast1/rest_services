package org.expressivesoftware.registration.marshall;

public class MarshallException extends RuntimeException {

	public MarshallException() {
		super();
	}

	public MarshallException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarshallException(String message) {
		super(message);
	}

	public MarshallException(Throwable cause) {
		super(cause);
	}

}
