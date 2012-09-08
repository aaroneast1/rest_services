package uk.co.and.comments;

import java.util.Set;

import uk.co.and.comments.model.Comment;

public interface CommentService extends CrudInterface {
	
	Set<Comment> get(Object id);
	
	boolean isActive(String parentId);

}
