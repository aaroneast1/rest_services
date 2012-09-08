package org.expressivesoftware.authentication.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

public class ServletModuleMapping extends ServletModule {
	
	
	@Override
	protected void configureServlets() {
		 serve("/auth/*").with(AuthenticatorServlet.class);
	}
	
}
