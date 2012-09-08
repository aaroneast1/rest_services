package org.expressivesoftware.notification.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.expressivesoftware.notification.Notifier;
import org.expressivesoftware.notification.email.model.Email;
import org.expressivesoftware.notification.email.model.Server;
import org.expressivesoftware.notification.email.model.SimpleAuthenticator;

public class SendMail implements Notifier<Email> {
	
	private static final String PARAM_SMTP_HOST = "mail.smtp.host";
	private static final String PROTOCOL_SMTP = "smtp";
	
	private final Server server;
	
	private Server getServer(){
		return server;
	}
	
	public SendMail(Server server){
		this.server = server;
	}
	
	public void send(Email t) {
		if(t == null) throw new IllegalArgumentException("No email details provided.");
		
		Properties properties = new Properties();
		properties.put(PARAM_SMTP_HOST, getServer().getHost());
		SimpleAuthenticator simpleAuthenticator = new SimpleAuthenticator(getServer().getUsername(), getServer().getPassword());
		Session session = Session.getDefaultInstance(properties, simpleAuthenticator);
		try {
			Transport transport = session.getTransport(PROTOCOL_SMTP);
			transport.connect(getServer().getHost(), getServer().getUsername(), getServer().getHost());
			transport.sendMessage(get(t,session), get(t.getTo()).toArray(new Address[t.getTo().size()]));
		} catch (MessagingException e) {
			throw new SendMailException(e);
		}
	}
	
	private Message get(Email email, Session session) throws MessagingException{
		Message msg = new MimeMessage(session);
		msg.setText(email.getBody());
		msg.setSubject(email.getSubject());
		msg.setFrom(get(email.getFrom()));
		msg.setRecipients(RecipientType.TO, get(email.getTo()).toArray(new Address[email.getTo().size()]));
		Address[] replyAddresses = new Address[1];
		replyAddresses[0] = get(email.getFrom());
		msg.setReplyTo(replyAddresses);
		return msg;
	}
	
	private List<Address> get(List<String> emails){
		List<Address> addresses = new ArrayList<Address>();
		for(String email : emails){
			addresses.add(get(email));
		}
		return addresses;
	}
	
	private Address get(String email){
		InternetAddress address = new InternetAddress();
		address.setAddress(email);
		return address;
	}

}
