package org.expressivesoftware.registration.modules;

import org.apache.log4j.Logger;
import org.expressivesoftware.registration.servlet.RegistrationServlet;

import com.google.inject.servlet.ServletModule;

public class ServletModuleMapping extends ServletModule {
	
	private static Logger LOG = Logger.getLogger(ServletModuleMapping.class);
	
	@Override
	protected void configureServlets() {
		LOG.info("configureing RegistrationServlet...");
		serve("/registration/*").with(RegistrationServlet.class);
		LOG.info("installing RegistrationModule...");
		install(new RegistrationModule());
	}

}
