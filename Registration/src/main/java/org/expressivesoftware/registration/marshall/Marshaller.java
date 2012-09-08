package org.expressivesoftware.registration.marshall;

import com.google.inject.ImplementedBy;

@ImplementedBy(UserMarshaller.class)
public interface Marshaller<T> {
	
	T marshallJson(String json);
	
	T marshallXml(String xml);
	
}
