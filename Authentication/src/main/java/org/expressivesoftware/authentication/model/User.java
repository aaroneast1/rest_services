package org.expressivesoftware.authentication.model;

public class User {

	private final String username;
	private final String password;
	private String sessionKey;
	private Status status;

	private User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static User newInstance(String username, String password) {
		return new User(username, password);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return hashCode() + "@{username=" + getUsername() + ", password="
				+ getPassword().hashCode() + "}";
	}

}
