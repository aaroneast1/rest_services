package org.expressivesoftware.registration.model;

import javax.persistence.Column;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Phone {
	
	private String mobile;
	private String home;
	private String work;
	
	@Column(name = "mobilePhone", length = 30)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "homePhone", length = 30)
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	@Column(name = "workPhone", length = 30)
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
