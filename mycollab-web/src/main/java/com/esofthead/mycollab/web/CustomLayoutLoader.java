package com.esofthead.mycollab.web;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.ui.CustomLayout;

public class CustomLayoutLoader {
	public static CustomLayout createLayout(String layoutId) {
		try {
			System.out.println("CLASS LOADER: "
					+ CustomLayoutLoader.class.getClassLoader());
			return new CustomLayout(CustomLayoutLoader.class.getClassLoader()
					.getResourceAsStream("layouts/" + layoutId + ".html"));
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
