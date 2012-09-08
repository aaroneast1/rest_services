package org.expressivesoftware.notification.email.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Server {

	private final String host;
	private final String username;
	private final String password;
	
	public Server(String host,
			String username,
			String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
