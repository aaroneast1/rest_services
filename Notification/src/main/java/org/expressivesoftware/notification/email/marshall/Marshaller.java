package org.expressivesoftware.notification.email.marshall;

import com.google.inject.ImplementedBy;

@ImplementedBy(EmailMarshaller.class)
public interface Marshaller<T> {
	
	T marshallJson(String json);
	
	T marshallXml(String xml);
	
}
