package org.expressivesoftware.notification.email.modules;

import org.apache.log4j.Logger;
import org.expressivesoftware.notification.servlet.NotifierServlet;

import com.google.inject.servlet.ServletModule;

public class ServletModuleMapping extends ServletModule {
	
	private static Logger LOG = Logger.getLogger(ServletModuleMapping.class);
	
	@Override
	protected void configureServlets() {
		LOG.info("configureing NotifierServlet...");
		serve("/email/*").with(NotifierServlet.class);
		LOG.info("installing MailModule...");
		install(new MailModule());
	}

}
