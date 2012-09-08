package uk.co.and.comments.tasks;

import org.apache.commons.logging.LogFactory;

import uk.co.and.comments.CrudInterface;
import uk.co.and.comments.model.Comment;

public class AddTask implements Task {

	private final Comment comment;
	private CrudInterface crud;

	public AddTask(Comment comment) {
		this.comment = comment;
	}

	@Override
	public void run() {
		try {
			crud.add(comment);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error(
					"Failed to to add comment: " + comment + " "
							+ e.getMessage(), e);
		}
	}

	@Override
	public void setCrudInterface(CrudInterface crud) {
		this.crud = crud;
	}

}
