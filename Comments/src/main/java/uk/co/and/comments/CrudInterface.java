package uk.co.and.comments;

import uk.co.and.comments.model.Comment;

public interface CrudInterface {
	
	void add(Comment comment);

	void update(Comment comment);

	void delete(Comment comment);

	Comment get(Comment comment);
	
}
