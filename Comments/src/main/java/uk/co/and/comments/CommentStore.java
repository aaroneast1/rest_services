package uk.co.and.comments;

import java.util.Set;

import uk.co.and.comments.model.Comment;

public interface CommentStore extends CrudInterface {
	
	Set<Comment> get(Object id);
	
	void add(Set<Comment> comments);
	
	void delete(Object id);

}
