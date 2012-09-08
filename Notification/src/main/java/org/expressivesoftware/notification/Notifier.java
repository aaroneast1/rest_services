package org.expressivesoftware.notification;


public interface Notifier<T> {
	
	void send(T t);
}
