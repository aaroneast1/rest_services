package org.expressivesoftware.registration;

public class InvalidRequestException extends IllegalArgumentException {

	public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestException(String s) {
		super(s);
	}

	public InvalidRequestException(Throwable cause) {
		super(cause);
	}

}
