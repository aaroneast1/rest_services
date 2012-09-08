package org.expressivesoftware.notification.email.modules;

import org.expressivesoftware.notification.Notifier;
import org.expressivesoftware.notification.email.SendMail;
import org.expressivesoftware.notification.email.marshall.EmailMarshaller;
import org.expressivesoftware.notification.email.marshall.Marshaller;
import org.expressivesoftware.notification.email.model.Server;

import com.google.inject.AbstractModule;

public class MailModule extends AbstractModule {
	
	/**
	 * TODO - need to learn more about injection this is dirty!
	 */
	
	@Override
	protected void configure() {
		bind(Notifier.class).toInstance(new SendMail(new Server("mail.expressivesoftware.org", "", "")));
		bind(Marshaller.class).to(EmailMarshaller.class);
	}
	
}
