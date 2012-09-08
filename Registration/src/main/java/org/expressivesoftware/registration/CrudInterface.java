package org.expressivesoftware.registration;


public interface CrudInterface<T> {
	
	void add(T t);
	
	T get(T t);
	
	void update(T t);
	
	void delete(T t);
	
}
