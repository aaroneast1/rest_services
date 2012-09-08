package uk.co.and.comments.marshall;

import java.util.Set;

import uk.co.and.comments.model.Comment;

public interface UnMarshaller {
	
	String unMarshall(Comment comment, MediaType mediaType);
	
	String unMarshall(Set<Comment> comments, MediaType mediaType);
	
}
