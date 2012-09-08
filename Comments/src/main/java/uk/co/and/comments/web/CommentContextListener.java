package uk.co.and.comments.web;


import org.apache.log4j.Logger;

import uk.co.and.comments.modules.ServletModuleMapping;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class CommentContextListener extends GuiceServletContextListener {
	
	private static Logger LOG = Logger.getLogger(CommentContextListener.class);
	
	@Override
	protected Injector getInjector() {
		LOG.info("Creating ServletModuleMapping...");
		return Guice.createInjector(new ServletModuleMapping());
	}

}
