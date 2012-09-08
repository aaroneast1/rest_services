package uk.co.and.comments;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;

import uk.co.and.comments.model.Comment;
import uk.co.and.comments.tasks.AddTask;
import uk.co.and.comments.tasks.DeleteTask;
import uk.co.and.comments.tasks.UpdateTask;

public class CommentServiceImpl implements CommentService {

	private static final Log LOG = LogFactory.getLog(CommentServiceImpl.class);
	private final BlockingQueue<Runnable> cacheQueue;
	private final BlockingQueue<Runnable> persistenceQueue;
	private final CommentStore store;

	@Inject
	public CommentServiceImpl(BlockingQueue cacheQueue,
			BlockingQueue persistenceQueue, CommentStore store) {
		this.cacheQueue = cacheQueue;
		this.persistenceQueue = persistenceQueue;
		this.store = store;
	}

	@Override
	public void add(Comment comment) {
		print(comment, "add a comment");
		persistenceQueue.add(new AddTask(comment));
		cacheQueue.add(new AddTask(comment));
	}

	@Override
	public void delete(Comment comment) {
		print(comment, "delete a comment");
		persistenceQueue.add(new DeleteTask(comment));
		cacheQueue.add(new DeleteTask(comment));
	}

	@Override
	public Comment get(Comment comment) {
		print(comment, "get a comment");
		return store.get(comment);
	}

	@Override
	public void update(Comment comment) {
		print(comment, "update a comment");
		persistenceQueue.add(new UpdateTask(comment));
		cacheQueue.add(new UpdateTask(comment));
	}

	@Override
	public Set<Comment> get(Object id) {
		return store.get(id);
	}

	private void print(Object object, String message) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(message + ": " + object.toString());
		}
	}

	@Override
	public boolean isActive(String parentId) {
		return true;
	}

}
