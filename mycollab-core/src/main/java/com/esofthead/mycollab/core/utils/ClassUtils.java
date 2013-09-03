package com.esofthead.mycollab.core.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.core.MyCollabException;

public class ClassUtils {
	public static boolean instanceOf(Object o, Class<?>... classes) {
		for (Class<?> cls : classes) {
			if (cls.isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	public static Class<?> getInterfaceInstanceOf(Class cls, Class superCls) {
		Class[] interfaces = cls.getInterfaces();
		for (Class inter : interfaces) {
			if (superCls.isAssignableFrom(inter)) {
				return inter;
			}
		}

		return null;
	}

	public static List<Field> getInheritedFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	public static Field getField(Class<?> type, String fieldname) {
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			try {
				return c.getDeclaredField(fieldname);
			} catch (Exception e) {
				// do nothing
			}
		}

		throw new MyCollabException("Can not find field " + fieldname
				+ " of class " + type.getName());
	}
}
