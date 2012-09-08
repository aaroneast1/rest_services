package org.expressivesoftware.authentication;

import org.expressivesoftware.authentication.model.User;

public interface LoginStore {
	
	String add(User user);
	
	User getUser(String sessionKey) throws UserNotFoundException, SessionTimeoutException;
	
	User update(User user) throws UserNotFoundException, SessionTimeoutException;
	
	void remove(String sessionKey);
	
	void remove(User user);
	
}
