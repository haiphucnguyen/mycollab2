package com.esofthead.mycollab.core.utils;

public class ClassUtils {
	public static boolean instanceOf(Object o, Class<?>... classes) {
		for (Class<?> cls : classes) {
			if (cls.isInstance(o)) {
				return true;
			}
		}
		return false;
	}
}
