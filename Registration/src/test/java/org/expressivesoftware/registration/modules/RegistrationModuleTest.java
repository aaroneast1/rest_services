package org.expressivesoftware.registration.modules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.expressivesoftware.registration.RegistraInterface;
import org.expressivesoftware.registration.db.DaoInterface;
import org.expressivesoftware.registration.marshall.Marshaller;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class RegistrationModuleTest {
	
	@Test
	public void testSuccessfulInstantiationOfRegistrationModule() throws Exception {
		
		Injector injector = Guice.createInjector(new RegistrationModule());
		RegistraInterface registra = injector.getInstance(RegistraInterface.class);
		DaoInterface registraDao = injector.getInstance(DaoInterface.class);
		Marshaller marshaller = injector.getInstance(Marshaller.class);
		
		assertNotNull(registra);
		assertNotNull(registraDao);
		assertNotNull(marshaller);
		
		assertEquals(registra, injector.getInstance(RegistraInterface.class));
		assertEquals(registraDao, injector.getInstance(DaoInterface.class));
		assertEquals(marshaller, injector.getInstance(Marshaller.class));
		
	}

}
