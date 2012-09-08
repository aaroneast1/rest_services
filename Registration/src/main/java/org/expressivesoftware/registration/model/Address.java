package org.expressivesoftware.registration.model;

import javax.persistence.Column;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Address {
	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String county;
	private String postcode;
	private String country;
	
	@Column(name = "addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	@Column(name = "addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	@Column(name = "addressLine3")
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "county")
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	@Column(name = "postcode")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
