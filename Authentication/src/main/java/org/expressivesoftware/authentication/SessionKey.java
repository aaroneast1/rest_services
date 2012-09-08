package org.expressivesoftware.authentication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class SessionKey {

	private Set<String> keys = new HashSet<String>();

	public SessionKey() {
		push(100);
	}

	public String getNewKey() {
		return UUID.randomUUID().toString();
	}

	public String getKey() {
		Iterator<String> iter = keys.iterator();
		if (keys.isEmpty())
			push(10);
		String key = null;
		while (iter.hasNext()) {
			key = iter.next();
			iter.remove();
			break;
		}
		return key;
	}

	private void push(int size) {
		int i = 0;
		while (i < size) {
			keys.add(getNewKey());
			i++;
		}
	}
}
