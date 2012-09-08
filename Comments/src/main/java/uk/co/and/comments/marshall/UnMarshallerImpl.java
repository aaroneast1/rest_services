package uk.co.and.comments.marshall;

import java.util.Set;

import uk.co.and.comments.model.Comment;

public class UnMarshallerImpl implements UnMarshaller {

	@Override
	public String unMarshall(Comment comment, MediaType mediaType) {
		return null;
	}

	@Override
	public String unMarshall(Set<Comment> comments, MediaType mediaType) {
		return null;
	}

}
