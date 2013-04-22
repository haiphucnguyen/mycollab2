package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.ApplicationProperties;

public class ResourceResolver {
	public static String getResourceLink(String relativePath) {
		return ApplicationProperties.getProperty(ApplicationProperties.APP_URL)
				+ "assets/" + relativePath;
	}
}
