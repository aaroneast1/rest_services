package uk.co.and.comments.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CommentImpl implements Comment {
	
	private String id;
	private String parentId;
	private boolean active;
	private final Date createDate;
	private final Person person;
	private final Location location;
	private final String subject;
	private final String comment;
	
	protected CommentImpl(String id, String parentId, boolean active, Date createDate, Person person, Location location, String subject, String comment){
		this.id = id;
		this.parentId = parentId;
		this.active = active;
		this.createDate = createDate;
		this.person = person;
		this.location = location;
		this.subject = subject;
		this.comment = comment;
	}
	
	public static Comment getInstance(String id, String parentId, boolean active, Date createDate, Person person, Location location, String subject, String comment){
		return new CommentImpl(id, parentId, active, createDate, person, location, subject, comment);
	}
	
	public static Comment getIdInstance(String id){
		if(id == null) return null;
		return new CommentImpl(id, null, false, null, null, null, null, null);
	}
	
	public static Comment getParentIdInstance(String parentId){
		return new CommentImpl(null, parentId, false, null, null, null, null, null);
	}
	
	public String getId() {
		return id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Person getPerson() {
		return person;
	}

	public Location getLocation() {
		return location;
	}

	public String getComment() {
		return comment;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public boolean isActive() {
		return active;
	}

	public String getParentId() {
		return parentId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0; 
		if(id != null){
			hashCode = id.hashCode();
		}
		if(parentId != null){
			hashCode = hashCode + parentId.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public boolean equals(Object object){
		if(object == null){
			return false;
		}
		
		if(this.hashCode() == object.hashCode()){
			return true;
		}
		
		return false;
	}

}
