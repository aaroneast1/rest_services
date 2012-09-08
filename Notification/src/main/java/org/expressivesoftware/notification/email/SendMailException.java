package org.expressivesoftware.notification.email;

public class SendMailException extends RuntimeException {

	private static final long serialVersionUID = -6293328871316330200L;

	public SendMailException() {
		super();
	}

	public SendMailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendMailException(String message) {
		super(message);
	}

	public SendMailException(Throwable cause) {
		super(cause);
	}
}
