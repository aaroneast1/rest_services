package uk.co.and.comments.model;

import java.util.Date;

public interface Comment {
	
	String getId();
	
	String getParentId();
	
	Date getCreateDate();
	
	Person getPerson();
	
	Location getLocation();
	
	String getComment();
	
	boolean isActive();
	

}
