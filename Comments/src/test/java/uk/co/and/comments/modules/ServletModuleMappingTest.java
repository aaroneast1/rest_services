package uk.co.and.comments.modules;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServletModuleMappingTest {
	
	@Test
	public void testSuccessfulInstantiationOfServletModule() {
		ServletModuleMapping servletModuleMapping = new ServletModuleMapping();
		assertNotNull(servletModuleMapping);
	}
	
}
