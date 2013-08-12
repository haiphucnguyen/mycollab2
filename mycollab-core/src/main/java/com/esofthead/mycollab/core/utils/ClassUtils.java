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

	public static Class<?> getInterfaceInstanceOf(Class cls, Class superCls) {
		Class[] interfaces = cls.getInterfaces();
		for (Class inter : interfaces) {
			System.out.println(inter.getName());
			if (superCls.isAssignableFrom(inter)) {
				return inter;
			}
		}

		return null;
	}
}
