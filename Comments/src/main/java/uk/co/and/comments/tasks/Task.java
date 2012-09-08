package uk.co.and.comments.tasks;

import uk.co.and.comments.CrudInterface;

public interface Task extends Runnable {
	
	void setCrudInterface(CrudInterface crud);

}
