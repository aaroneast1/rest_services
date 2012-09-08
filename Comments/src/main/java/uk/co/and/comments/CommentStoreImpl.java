package uk.co.and.comments;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import uk.co.and.comments.model.Comment;

public class CommentStoreImpl implements CommentStore {

	private final Map<String, Comment> commentStore;
	private final Map<Object, Set<Comment>> commentSetStore;
	
	//TODO - Need to inject configuration for stores
	//TODO - Need to wrap comments in weakreference.class
	
	public CommentStoreImpl() {
		this.commentStore = new ConcurrentHashMap<String, Comment>();
		this.commentSetStore = new ConcurrentHashMap<Object, Set<Comment>>();
	}

	@Override
	public void add(Set<Comment> comments) {
		if (comments == null || comments.isEmpty()) {
			throw new IllegalArgumentException("null or empty collection");
		}

		commentSetStore.put(comments.iterator().next().getParentId(), comments);
	}

	@Override
	public void delete(Object id) {
		commentSetStore.remove(id);
	}

	@Override
	public Set<Comment> get(Object id) {
		return commentSetStore.get(id);
	}

	@Override
	public void add(Comment comment) {
		assertNotNull(comment);
		commentStore.put(comment.getId(), comment);
	}

	@Override
	public void delete(Comment comment) {
		assertNotNull(comment);
		commentStore.remove(comment.getId());
	}

	@Override
	public Comment get(Comment comment) {
		assertNotNull(comment);
		return commentStore.get(comment.getId());
	}

	@Override
	public void update(Comment comment) {
		assertNotNull(comment);
		commentStore.put(comment.getId(), comment);
	}

	private void assertNotNull(Comment comment) {
		if (comment == null && comment.getId() == null
				&& comment.getParentId() == null) {
			throw new IllegalArgumentException("invalid comment: " + comment);
		}
	}

}
