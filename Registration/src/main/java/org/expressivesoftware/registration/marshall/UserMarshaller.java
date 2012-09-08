package org.expressivesoftware.registration.marshall;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import org.expressivesoftware.registration.model.Address;
import org.expressivesoftware.registration.model.ContactDetails;
import org.expressivesoftware.registration.model.Person;
import org.expressivesoftware.registration.model.Phone;
import org.expressivesoftware.registration.model.User;

public class UserMarshaller implements Marshaller<User> {
	
	public static final String CONTACT_DETAILS = "contactDetails";
	public static final String PERSON = "person";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String DOB = "dob";
	public static final String DATE_FAILURE_MESSAGE = "Invalid date format.";
	public final SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
	public static final String DATE_FORMAT = "dd.MM.yyyy";
	
	public User marshallJson(String json) {
		return getUser(json);
	}
	
	private User getUser(String json) {
		if(json == null)return null;
		JavaBeanMarshaller<User> marshaller = new JavaBeanMarshaller<User>(ContactDetails.class, Person.class);
		User user = marshaller.get(json, new User());
		JSONObject jsonObject = JSONObject.fromObject(json);
		user.setContactDetails(getContactDetails(jsonObject.getString(CONTACT_DETAILS)));
		user.setPerson(getPerson(jsonObject.getString(PERSON)));
		return user;
	}

	private Address getAddress(String json){
		JavaBeanMarshaller<Address> javaBeanMarshaller = new JavaBeanMarshaller<Address>();
		return javaBeanMarshaller.get(json, new Address());
	}
	
	private Phone getPhone(String json){
		JavaBeanMarshaller<Phone> javaBeanMarshaller = new JavaBeanMarshaller<Phone>();
		return javaBeanMarshaller.get(json, new Phone());
	}
	
	private ContactDetails getContactDetails(String json){
		JavaBeanMarshaller<ContactDetails> marshaller = new JavaBeanMarshaller<ContactDetails>(Phone.class, Address.class);
		ContactDetails contactDetails = marshaller.get(json, new ContactDetails());
		JSONObject jsonObject = JSONObject.fromObject(json);
		contactDetails.setAddress(getAddress(jsonObject.getString(ADDRESS)));
		contactDetails.setPhone(getPhone(jsonObject.getString(PHONE)));
		return contactDetails;
	}
	
	private Person getPerson(String json){
		JavaBeanMarshaller<Person> marshaller = new JavaBeanMarshaller<Person>();
		Person person = marshaller.get(json, new Person());
		try {
			person.setDob(df.parse(JSONObject.fromObject(json).getString(DOB)));
		} catch (ParseException e) {
			throw new MarshallException(DATE_FAILURE_MESSAGE,e);
		}
		return person;
	}

	public User marshallXml(String xml) {
		return null;
	}

}
