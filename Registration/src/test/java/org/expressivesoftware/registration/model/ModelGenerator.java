package org.expressivesoftware.registration.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class ModelGenerator {
	
	public static Person getPerson() {
		Person person = new Person();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			person.setDob(df.parse("02.11.1979"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		person.setFirstname("Aaron");
		person.setLastname("East");
		person.setMale(true);
		return person;
	}
	
	public static Address getAddress(){
		Address address = new Address();
		address.setAddressLine1("11 Arundel Avenue");
		address.setCity("London");
		address.setCountry("UK");
		address.setPostcode("SM44DP");
		return address;
	}
	
	public static ContactDetails getContactDetails() {
		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setAddress(getAddress());
		contactDetails.setEmail("aaroneast1@gmail.com");
		contactDetails.setPhone(getPhone());
		return contactDetails;
	}
	
	public static Phone getPhone(){
		Phone phone = new Phone();
		phone.setHome("+44(0)2082863332");
		phone.setMobile("+44(0)7525811799");
		phone.setWork("+44(0)2075425512");
		return phone;
	}
	
	public static User getUser() {
		User user = new User();
		user.setPassword("test");
		user.setUsername("aaroneast1");
		user.setPerson(getPerson());
		user.setContactDetails(getContactDetails());
		return user;
	}
	
	public static User getExistingUser(){
		User user = getUser();
		user.setId(1000);
		return user;
	}
	
	@Test
	public void testSuccessfulCreationOfAPerson() throws Exception {
		assertNotNull(getPerson());
	}
	
	@Test
	public void testSuccessfulCreationOfAPhone() throws Exception {
		assertNotNull(getPhone());
	}
	
	@Test
	public void testSuccessfulCreationOfAContactDetails() throws Exception {
		assertNotNull(getContactDetails());
	}
	
	@Test
	public void testSuccessfulCreationOfAUser() throws Exception {
		assertNotNull(getUser());
	}

}
