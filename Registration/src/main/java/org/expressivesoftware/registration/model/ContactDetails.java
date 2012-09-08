package org.expressivesoftware.registration.model;

import javax.persistence.Column;
import javax.persistence.Embedded;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContactDetails {
	
	private String email;
	private Phone phone;
	private Address address;
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Embedded
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
