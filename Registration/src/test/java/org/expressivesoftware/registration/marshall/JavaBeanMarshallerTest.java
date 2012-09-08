package org.expressivesoftware.registration.marshall;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.expressivesoftware.registration.model.Address;
import org.expressivesoftware.registration.model.ContactDetails;
import org.expressivesoftware.registration.model.ModelGenerator;
import org.expressivesoftware.registration.model.Person;
import org.expressivesoftware.registration.model.Phone;
import org.junit.Ignore;
import org.junit.Test;

public class JavaBeanMarshallerTest {

	@Test
	public void testSuccessfulMarshallAddress() throws Exception {
		JavaBeanMarshaller<Address> marshaller = new JavaBeanMarshaller<Address>();
		Address address = marshaller.get(JSONGenerator.getAddress(),
				new Address());
		assertAddress(address);
	}

	@Test
	public void testSuccessfulMarshallPerson() throws Exception {
		JavaBeanMarshaller<Person> marshaller = new JavaBeanMarshaller<Person>();
		Person person = marshaller.get(JSONGenerator.getPerson(), new Person());
		assertPerson(person);
	}

	@Test
	public void testSuccessfulMarshallPhone() throws Exception {
		JavaBeanMarshaller<Phone> marshaller = new JavaBeanMarshaller<Phone>();
		Phone phone = marshaller.get(JSONGenerator.getPhone(), new Phone());
		assertPhone(phone);
	}

	@Test
	public void testSuccessfulMarshallContactDetails() throws Exception {
		JavaBeanMarshaller<ContactDetails> marshaller = new JavaBeanMarshaller<ContactDetails>(
				Phone.class, Address.class);
		ContactDetails contactDetails = marshaller.get(JSONGenerator
				.getContactDetails(), new ContactDetails());
		assertNotNull(contactDetails);
		assertNull(contactDetails.getAddress());
		assertNull(contactDetails.getPhone());
		assertEquals(contactDetails.getEmail(), ModelGenerator
				.getContactDetails().getEmail());
		JSONObject contactDetailsJSON = JSONObject.fromObject(JSONGenerator
				.getContactDetails());
		JavaBeanMarshaller<Address> addressMarshaller = new JavaBeanMarshaller<Address>();
		Address address = addressMarshaller.get(contactDetailsJSON
				.getString("address"), new Address());
		assertAddress(address);

	}
	
	@Test
	public void testInvalidUseOfJavaBeanMarshaller() throws Exception {
		JavaBeanMarshaller<ContactDetails> marshaller = new JavaBeanMarshaller<ContactDetails>();
		try{
			ContactDetails contactDetails = marshaller.get(JSONGenerator.getContactDetails(), new ContactDetails());
		}catch(MarshallException e){
			//Should fail with the following excpetion
		}
		
	}

	@Ignore
	private void assertPhone(Phone phone) {
		assertNotNull(phone);
		assertEquals(phone.getHome(), ModelGenerator.getPhone().getHome());
		assertEquals(phone.getMobile(), ModelGenerator.getPhone().getMobile());
		assertEquals(phone.getWork(), ModelGenerator.getPhone().getWork());
	}

	@Ignore
	private void assertAddress(Address address) {
		assertNotNull(address);
		assertEquals(address.getAddressLine1(), ModelGenerator.getAddress()
				.getAddressLine1());
		assertEquals(address.getAddressLine2(), ModelGenerator.getAddress()
				.getAddressLine2() == null ? "" : ModelGenerator.getAddress()
				.getAddressLine2());
		assertEquals(address.getAddressLine3(), ModelGenerator.getAddress()
				.getAddressLine3() == null ? "" : ModelGenerator.getAddress()
				.getAddressLine3());
		assertEquals(address.getCity(), ModelGenerator.getAddress().getCity());
		assertEquals(address.getCounty(), ModelGenerator.getAddress()
				.getCounty() == null ? "" : ModelGenerator.getAddress()
				.getCounty());
		assertEquals(address.getCountry(), ModelGenerator.getAddress()
				.getCountry());
		assertEquals(address.getPostcode(), ModelGenerator.getAddress()
				.getPostcode());
	}

	@Ignore
	private void assertPerson(Person person) {
		assertNotNull(person);
		assertEquals(person.getDob(), null);
		assertEquals(person.getFirstname(), ModelGenerator.getPerson()
				.getFirstname());
		assertEquals(person.getLastname(), ModelGenerator.getPerson()
				.getLastname());
	}

}
