package org.expressivesoftware.registration.model;

import org.expressivesoftware.registration.db.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;


public class UserTest {
	
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Test
	public void testVerySimplePersistence() {
		Session session = sessionFactory.getCurrentSession();
	    Transaction tx = session.beginTransaction();
	    User user = new User();
	    user.setUsername("aaroneast1");
	    user.setPassword("12345");
	    session.save(user);
	    tx.commit();
	}
	
	@Test
	public void testCompleteUserObject() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
	    User user = ModelGenerator.getUser();
	    session.save(user);
	    tx.commit();
	}

}
