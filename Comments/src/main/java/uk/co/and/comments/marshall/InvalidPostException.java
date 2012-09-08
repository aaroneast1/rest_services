package uk.co.and.comments.marshall;

public class InvalidPostException extends RuntimeException {

	public InvalidPostException() {
		super();
	}

	public InvalidPostException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidPostException(String arg0) {
		super(arg0);
	}

	public InvalidPostException(Throwable arg0) {
		super(arg0);
	}

}
