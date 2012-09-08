package org.expressivesoftware.registration.modules;

import static org.junit.Assert.*;

import org.expressivesoftware.registration.modules.ServletModuleMapping;
import org.junit.Test;

public class ServletModuleMappingTest {
	
	@Test
	public void testSuccessfulInstantiationOfServletModuleMapping() throws Exception {
		ServletModuleMapping servletModuleMapping = new ServletModuleMapping();
		assertNotNull(servletModuleMapping);
	}

}
