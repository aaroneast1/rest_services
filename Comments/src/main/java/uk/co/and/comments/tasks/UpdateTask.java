package uk.co.and.comments.tasks;

import org.apache.commons.logging.LogFactory;

import uk.co.and.comments.CrudInterface;
import uk.co.and.comments.model.Comment;

public class UpdateTask implements Task {
	
	private final Comment comment;
	private CrudInterface crud;
	
	public UpdateTask(Comment comment){
		this.comment = comment;
	}
	
	@Override
	public void run() {
		try {
			crud.update(comment);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error(
					"Failed to to update comment: " + comment + " "
							+ e.getMessage(), e);
		}
	}

	@Override
	public void setCrudInterface(CrudInterface crud) {
		this.crud = crud;
	}

}
