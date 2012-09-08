package org.expressivesoftware.registration.servlet;

import org.apache.log4j.Logger;
import org.expressivesoftware.registration.modules.ServletModuleMapping;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class RegistrationServletContextListener extends GuiceServletContextListener {
	
	private static Logger LOG = Logger.getLogger(RegistrationServletContextListener.class);
		
	@Override
	protected Injector getInjector() {
		LOG.info("Creating ServletModuleMapping...");
		return Guice.createInjector(new ServletModuleMapping());
	}

}
