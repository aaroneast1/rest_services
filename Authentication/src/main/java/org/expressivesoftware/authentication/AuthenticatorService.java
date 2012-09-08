package org.expressivesoftware.authentication;

import org.expressivesoftware.authentication.model.Status;
import org.expressivesoftware.authentication.model.User;

public interface AuthenticatorService {
	
	void login(User user);
	
	void logout(User user);
	
	String getNewSessionKey(User user);
	
	Status get(User user);
}
