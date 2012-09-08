package org.expressivesoftware.notification.servlet;

import org.apache.log4j.Logger;
import org.expressivesoftware.notification.email.modules.ServletModuleMapping;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class IphoneServletContextListener extends GuiceServletContextListener {
	
	private static Logger LOG = Logger.getLogger(IphoneServletContextListener.class);
		
	@Override
	protected Injector getInjector() {
		LOG.info("Creating ServletModuleMapping...");
		return Guice.createInjector(new ServletModuleMapping());
	}

}
