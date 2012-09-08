package uk.co.and.comments;

public class InActiveCommentException extends RuntimeException {

	public InActiveCommentException() {
		super();
	}

	public InActiveCommentException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InActiveCommentException(String arg0) {
		super(arg0);
	}

	public InActiveCommentException(Throwable arg0) {
		super(arg0);
	}

}
