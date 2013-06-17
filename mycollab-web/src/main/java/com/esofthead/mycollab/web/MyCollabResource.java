package com.esofthead.mycollab.web;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;

public abstract class MyCollabResource {

	private static MyCollabResource impl;

	static {
		String storageSystem = ApplicationProperties.getString(
				ApplicationProperties.STORAGE_SYSTEM, "file");
		if (StorageSetting.S3_STORAGE_SYSTEM.equals(storageSystem.trim())) {
			impl = new S3Resource();
		} else {
			impl = new VaadinThemeResource();
		}
	}

	protected abstract Resource generateResource(String resourceId);

	public static Resource newResource(String resourceId) {
		return impl.generateResource(resourceId);
	}

	public static class S3Resource extends MyCollabResource {

		private static String S3_ASSETS = "https://s3.amazonaws.com/mycollab_assets/%s";

		@Override
		protected Resource generateResource(String resourceId) {
			return new ExternalResource(String.format(S3_ASSETS, resourceId));
		}

	}

	public static class VaadinThemeResource extends MyCollabResource {

		@Override
		protected Resource generateResource(String resourceId) {
			return new ThemeResource(resourceId);
		}

	}
}
