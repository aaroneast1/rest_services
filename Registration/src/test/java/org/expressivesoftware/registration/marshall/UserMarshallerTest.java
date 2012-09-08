package org.expressivesoftware.registration.marshall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.expressivesoftware.registration.model.ModelGenerator;
import org.expressivesoftware.registration.model.User;
import org.junit.Test;

public class UserMarshallerTest {
	
	@Test
	public void successfullyMarshallJSONToEmail() throws Exception {
		Marshaller<User> marshaller = new UserMarshaller();
		
		User user = marshaller.marshallJson(JSONGenerator.getExistingUserAsJSON());
		assertNotNull(user);
		assertNotNull(user.getContactDetails());
		assertNotNull(user.getId());
		assertNotNull(user.getPerson());
		assertEquals(user.getUsername(),ModelGenerator.getUser().getUsername());
		assertEquals(user.getPassword(), ModelGenerator.getUser().getPassword());
	}
	
}
