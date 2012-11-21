package com.esofthead.mycollab.vaadin.mvp;

public class ViewManager {
	public static <T extends View> T getView(Class<T> viewClass) {
		try {
			return (T) viewClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}
}
