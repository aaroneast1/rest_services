package org.expressivesoftware.registration.modules;

import org.expressivesoftware.registration.Registra;
import org.expressivesoftware.registration.RegistraInterface;
import org.expressivesoftware.registration.db.DaoInterface;
import org.expressivesoftware.registration.db.HibernateUtil;
import org.expressivesoftware.registration.db.RegistraDao;
import org.expressivesoftware.registration.marshall.Marshaller;
import org.expressivesoftware.registration.marshall.UserMarshaller;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class RegistrationModule extends AbstractModule {

	//TODO - Need to make sure DAO is a singleton
	
	@Override
	protected void configure() {
		bind(RegistraInterface.class).to(Registra.class);
		bind(Registra.class).in(Scopes.SINGLETON);
		bind(Marshaller.class).to(UserMarshaller.class);
		bind(UserMarshaller.class).in(Scopes.SINGLETON);
		bind(DaoInterface.class).toInstance(new RegistraDao(HibernateUtil.getSessionFactory()));
	}
	
}
