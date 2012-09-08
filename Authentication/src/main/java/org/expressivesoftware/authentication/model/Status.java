package org.expressivesoftware.authentication.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Status {
	
	private boolean loggedIn;
	private Date createDate;
	private Date modifiedDate;
	private Date timeout;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
