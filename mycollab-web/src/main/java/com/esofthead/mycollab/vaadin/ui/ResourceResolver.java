package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.web.AppContext;

public class ResourceResolver {
	public static String getResourceLink(String relativePath) {
		return AppContext.getSiteUrl() + "assets/" + relativePath;
	}
}
