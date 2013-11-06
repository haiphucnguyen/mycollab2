package com.esofthead.mycollab.core.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esofthead.mycollab.core.MyCollabException;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDeSerializer {
	private static final Gson gson;

	static {
		gson = new GsonBuilder().setExclusionStrategies(
				new MyExclusionStrategy()).create();
	}

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T fromJson(String value, Class<T> type) {
		T ins = gson.fromJson(value, type);
		if (ins == null) {
			try {
				return type.newInstance();
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}

		return ins;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD })
	public static @interface Exclude {
		// Field tag only annotation
	}

	private static class MyExclusionStrategy implements ExclusionStrategy {

		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}

		public boolean shouldSkipField(FieldAttributes f) {
			return f.getAnnotation(Exclude.class) != null;
		}
	}
}
