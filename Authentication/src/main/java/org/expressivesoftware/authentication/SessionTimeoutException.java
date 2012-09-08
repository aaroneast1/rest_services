package org.expressivesoftware.authentication;

public class SessionTimeoutException extends RuntimeException {

	public SessionTimeoutException() {
		super();
	}

	public SessionTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionTimeoutException(String message) {
		super(message);
	}

	public SessionTimeoutException(Throwable cause) {
		super(cause);
	}
}
