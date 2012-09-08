package uk.co.and.comments.modules;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PropertiesModuleTest {
	
	@Test
	public void testSuccessfulLoadingOfDBProperties() {
		Injector i = Guice.createInjector(new PropertiesModule());
		PropertiesExample propertiesExample = i.getInstance(PropertiesExample.class);
		assertEquals(propertiesExample.getUrl(),"jdbc:mysql://localhost/comments");
		assertEquals(propertiesExample.getPassword(),"c0mm3nts");
		assertEquals(propertiesExample.getUsername(), "comments");
	}

}
