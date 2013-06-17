package com.esofthead.mycollab.web;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;

public abstract class MyCollabResource {

	private static MyCollabResource impl;

	static {
		impl = new VaadinThemeResource();
	}

	protected abstract Resource generateResource(String resourceId);

	public static Resource newResource(String resourceId) {
		return impl.generateResource(resourceId);
	}

	public static class S3Resource extends MyCollabResource {

		@Override
		protected Resource generateResource(String resourceId) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class VaadinThemeResource extends MyCollabResource {

		@Override
		protected Resource generateResource(String resourceId) {
			return new ThemeResource(resourceId);
		}

	}
}
