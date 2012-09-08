package org.expressivesoftware.registration.marshall;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

public class JavaBeanMarshaller<T> {

	private List<Class> types;

	public JavaBeanMarshaller(Class... types) {
		this();
		this.types.addAll(Arrays.asList(types));
	}

	public JavaBeanMarshaller() {
		this.types = new ArrayList<Class>();
		this.types.add(Date.class);
	}

	private boolean contains(List<Class<?>> classes) {
		boolean containsClass = false;
		for (Class clazz : types) {
			containsClass = classes.contains(clazz);
			if (containsClass)
				return true;
		}
		return containsClass;
	}

	public T get(String json, T t) {
		if (t == null || json == null)return null;

		JSONObject jsonObject = JSONObject.fromObject(json);

		for (Method method : t.getClass().getDeclaredMethods()) {
			if (!method.getName().startsWith("set", 0)) {
				continue;
			}

			String fieldName = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4); 

			try {

				if (contains(Arrays.asList(method.getParameterTypes()))) {
					continue;
				}

				method.invoke(t, jsonObject.get(fieldName));
			} catch (IllegalArgumentException e) {
				throw new MarshallException(e);
			} catch (IllegalAccessException e) {
				throw new MarshallException(e);
			} catch (InvocationTargetException e) {
				throw new MarshallException(e);
			}
		}

		return t;
	}

}
