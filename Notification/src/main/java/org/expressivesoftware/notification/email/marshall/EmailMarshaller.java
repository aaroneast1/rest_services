package org.expressivesoftware.notification.email.marshall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.expressivesoftware.notification.email.model.Email;

public class EmailMarshaller implements Marshaller<Email> {

	private static Logger LOG = Logger.getLogger(EmailMarshaller.class);
	
	//TODO - will need to handle character returns in body as json doesn't handle certain characters ie. \
	
	public Email marshallJson(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		List<String> to = new ArrayList<String>();
		List<String> cc = new ArrayList<String>();
		List<String> bcc = new ArrayList<String>();
		Email email = new Email(null, null);

		for (Method method : Email.class.getDeclaredMethods()) {
			if (!method.getName().startsWith("set", 0)) {
				continue;
			}

			String fieldName = method.getName().substring(3).toLowerCase();

			try {
				method.invoke(email, jsonObject.get(fieldName));
			} catch (IllegalArgumentException e) {
				LOG.error(e);
				throw new MarshallException(e);
			} catch (IllegalAccessException e) {
				LOG.error(e);
				throw new MarshallException(e);
			} catch (InvocationTargetException e) {
				LOG.error(e);
				throw new MarshallException(e);
			}
		}
		return email;
	}
	
	
	public Email marshallXml(String xml) {
		//TODO - implement xml marshalling.
		return null;
	}

}
