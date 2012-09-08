package org.expressivesoftware.registration.db;

import org.apache.log4j.Logger;
import org.expressivesoftware.registration.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.inject.Inject;

public class RegistraDao implements DaoInterface {

	private static Logger LOG = Logger.getLogger(RegistraDao.class);
	private final SessionFactory sessionFactory;
	
	@Inject
	public RegistraDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(User user) {
		Integer id = save(user);
		user.setId(id);
	}
	
	private Integer save(User user){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(user);
		tx.commit();
		return id;
	}

	public void delete(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
	}

	public User get(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		User loadedUser = (User)session.load(User.class, user.getId());
		tx.commit();
		return loadedUser;
	}

	public void update(User user) {
		save(user);
	}

}
