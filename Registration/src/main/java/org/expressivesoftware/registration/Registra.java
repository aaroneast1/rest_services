package org.expressivesoftware.registration;

import org.apache.log4j.Logger;
import org.expressivesoftware.registration.model.User;

import com.google.inject.Inject;

public class Registra implements RegistraInterface {
	
	private static Logger LOG = Logger.getLogger(Registra.class);
	
	private final RegistraInterface registraDao;
	
	@Inject
	public Registra(RegistraInterface registraDao){
		this.registraDao = registraDao;
	}
	
	public void add(User user) {
		if(user==null)return;
		if(LOG.isDebugEnabled())LOG.debug("user="+user.toString());
		
		if(user.getId()!=null)throw new InvalidRequestException("Id is not null.");
		
		registraDao.add(user);
	}

	public void delete(User user) {
		if(user==null)return;
		if(LOG.isDebugEnabled())LOG.debug("user="+user.toString());
		
		if(user.getId()==null)throw new InvalidRequestException("No id provided.");
		
		registraDao.delete(user);
	}

	public User get(User user) {
		if(user==null)return null;
		if(LOG.isDebugEnabled())LOG.debug("user="+user.toString());
		
		if(validIdRequest(user))throw new InvalidRequestException("No id provided.");
		
		return registraDao.get(user);
	}
	
	private boolean validIdRequest(User user){
		String email = user.getContactDetails() == null ? null : user.getContactDetails().getEmail();
		
		if(user.getId()==null && user.getUsername() == null && email == null){
			return false;
		}
		
		return true;
	}

	public void update(User user) {
		if(user==null)return;
		if(LOG.isDebugEnabled())LOG.debug("user="+user.toString());
		
		if(validIdRequest(user))throw new InvalidRequestException("No id provided.");
		
		registraDao.update(user);
	}

}
