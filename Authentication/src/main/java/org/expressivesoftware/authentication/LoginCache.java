package org.expressivesoftware.authentication;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.expressivesoftware.authentication.model.Status;
import org.expressivesoftware.authentication.model.User;

public class LoginCache implements LoginStore {

	private final int timeout;
	private Map<String, User> cache = new ConcurrentHashMap<String, User>();

	public LoginCache(int timeout) {
		this.timeout = timeout;
	}
	
	public static String getNewKey(){
		
		return null;
	}

	public int getTimeout() {
		return timeout;
	}

	@Override
	public String add(User user) {
		Status status = new Status();
		status.setCreateDate(new Date());
		status.setLoggedIn(true);
		status.setTimeout(new Date(Long.valueOf(
				status.getCreateDate().getTime()).intValue()
				+ getTimeout()));
		user.setStatus(status);
		cache.put(user.getUsername(), user);
		return user.getSessionKey();
	}

	private boolean isTimedout(User user) {
		if (user == null)
			return true;
		if (user.getStatus() == null)
			return true;
		if (!user.getStatus().isLoggedIn())
			return true;
		if (user.getStatus().getModifiedDate() == null) {
			return user.getStatus().getTimeout().after(
					user.getStatus().getCreateDate());
		}
		return user.getStatus().getTimeout().after(
				user.getStatus().getModifiedDate());
	}

	@Override
	public User getUser(String sessionKey) throws UserNotFoundException {
		if (!cache.containsKey(sessionKey))
			throw new UserNotFoundException("No user exists with " + sessionKey
					+ " session key.");
		User user = cache.get(sessionKey);
		if (isTimedout(user))
			throw new SessionTimeoutException(
					"Session expired for sessionKey: " + sessionKey + ", user="
							+ user);
		return user;
	}

	@Override
	public void remove(String sessionKey) {
		cache.remove(sessionKey);
	}

	@Override
	public void remove(User user) {
		boolean found = false;
		int i = 0;
		while (!(found && i>10)) {
			i++;
			if (cache.containsValue(user)) {
				for (User u : cache.values()) {
					if (u.equals(user)) {
						if (!cache.containsKey(user.getSessionKey())) {
							break;
						}
						remove(user.getSessionKey());
						found = true;
					}
				}
			}
		}
	}

	@Override
	public User update(User user) throws UserNotFoundException,
			SessionTimeoutException {
		User cacheUser = getUser(user.getSessionKey());
		User newUser = User.newInstance(cacheUser.getUsername(), cacheUser
				.getPassword());
		Status status = cacheUser.getStatus();
		status.setModifiedDate(new Date());
		status.setTimeout(new Date(Long.valueOf(
				status.getModifiedDate().getTime()).intValue()
				+ getTimeout()));
		newUser.setStatus(status);
		remove(user);
		cache.put(newUser.getUsername(), newUser);
		return newUser;
	}
}
