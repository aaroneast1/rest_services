package uk.co.and.comments.modules;

import org.apache.log4j.Logger;

import uk.co.and.comments.web.CommentServlet;

import com.google.inject.servlet.ServletModule;

public class ServletModuleMapping extends ServletModule {
	
	private static Logger LOG = Logger.getLogger(ServletModuleMapping.class);
	
	@Override
	protected void configureServlets() {
		LOG.info("configuring CommentServlet...");
		serve("/*").with(CommentServlet.class);
		LOG.info("installing Comment module...");
		install(new CommentModule());
		LOG.info("installing Cache module...");
		install(new CacheModule());
	}

}
