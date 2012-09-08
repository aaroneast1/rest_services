package org.expressivesoftware.registration.db;

import static org.junit.Assert.*;

import org.expressivesoftware.registration.db.HibernateUtil;
import org.expressivesoftware.registration.db.RegistraDao;
import org.expressivesoftware.registration.model.ModelGenerator;
import org.expressivesoftware.registration.model.User;
import org.junit.Test;

public class RegistraDaoTest {
	
	@Test
	public void testSuccessfulAddUser(){
		RegistraDao dao = new RegistraDao(HibernateUtil.getSessionFactory());
		User newUser = ModelGenerator.getUser();
		dao.add(newUser);
		User getUser = dao.get(newUser);
		assertEquals(getUser.getId(),newUser.getId());
	}

}
