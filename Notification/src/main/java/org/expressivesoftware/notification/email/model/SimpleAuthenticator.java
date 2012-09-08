package org.expressivesoftware.notification.email.model;

import javax.mail.PasswordAuthentication;

public class SimpleAuthenticator extends javax.mail.Authenticator {
	
	private final PasswordAuthentication passwordAuthentication;
	
	public SimpleAuthenticator(String username, String password){
		this.passwordAuthentication = new PasswordAuthentication(username, password);
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return this.passwordAuthentication;
	}
}
