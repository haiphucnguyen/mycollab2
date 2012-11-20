package com.esofthead.mycollab.vaadin.mvp;

public class ViewManager {
	public static View getView(Class viewClass) {
		try {
			return (View) viewClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName());
		}
	}
}
