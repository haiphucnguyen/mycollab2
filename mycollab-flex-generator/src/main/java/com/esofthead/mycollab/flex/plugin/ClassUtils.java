package com.esofthead.mycollab.flex.plugin;

public class ClassUtils {
	public static String getPackage(Class cls) {
		String name = cls.getName();
		int lastIndex = name.lastIndexOf(".");
		if (lastIndex < 0) {
			return name;
		} else {
			return name.substring(0, lastIndex);
		}
	}
}
