package uk.co.and.comments.marshall;

import uk.co.and.comments.model.Comment;

public interface Marshaller {
	
	Comment marshall(String text, MediaType contentType);

}
