package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.ecm.StorageNames;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class GoogleDriveOAuthWindow extends
		CloudDriveIntegrationOAuthWindow {
	private static final long serialVersionUID = 1L;

	public GoogleDriveOAuthWindow() {
		super("Add GoogleDrive");
	}

	@Override
	protected String buildAuthUrl() {
		return null;
	}

	@Override
	protected String getStorageName() {
		return StorageNames.GOOGLEDRIVE;
	}
}
