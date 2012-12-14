package com.esofthead.mycollab.vaadin.mvp;

import java.util.WeakHashMap;

public class ViewManager {
	private static WeakHashMap<Class, Object> viewMap = new WeakHashMap<Class, Object>();

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(Class<T> viewClass) {
		try {
			T value = (T) viewMap.get(viewClass);
			if (value != null) {
				return value;
			} else {
				value = (T) viewClass.newInstance();
				viewMap.put(viewClass, value);
				return value;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}
}
