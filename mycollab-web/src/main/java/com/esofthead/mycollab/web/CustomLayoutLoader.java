package com.esofthead.mycollab.web;

import com.vaadin.ui.CustomLayout;

public class CustomLayoutLoader {
	public static CustomLayout createLayout(String layoutId) {
		try {
			return new CustomLayout(CustomLayoutLoader.class.getClassLoader()
					.getResourceAsStream("layouts/" + layoutId + ".html"));
		} catch (Exception e) {
			return CustomLayoutLoader.createLayout(layoutId);
		}
	}
}
