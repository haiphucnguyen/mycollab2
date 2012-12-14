package com.esofthead.mycollab.vaadin.mvp;

import java.util.WeakHashMap;

public class ViewManager {
	private static WeakHashMap<Class, Object> viewMap = new WeakHashMap<Class, Object>();
	
	public static <T extends View> T getView(Class<T> viewClass) {
		try {
			
			return (T) viewClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}
}
