package uk.co.and.comments.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Location {
	
	private final String address;
	private final String address1;
	private final String address2;
	private final String city;
	private final String county;
	private final String country;
	private final String postCode;
	
	private Location(String address, String address1, String address2, String city, String county, String country, String postCode){
		this.address = address;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.country = country;
		this.postCode = postCode;
	}
	
	public static Location getInstance(String address, String address1, String address2, String city, String county, String country, String postCode){
		return new Location(address, address1, address2, city, county, country, postCode);
	}
	
	public String getAddress() {
		return address;
	}

	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCity() {
		return city;
	}
	public String getCounty() {
		return county;
	}
	public String getCountry() {
		return country;
	}
	public String getPostCode() {
		return postCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
