package uk.co.and.comments.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Person {
	
	private final String firstname;
	private final String lastname;
	
	public Person(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFullname(){
		return getFirstname() + " " + getLastname();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
