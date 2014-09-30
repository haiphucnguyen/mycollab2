package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.module.ecm.StorageNames;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class GoogleDriveOAuthWindow extends
		DefaultCloudDriveOAuthWindow {
	private static final long serialVersionUID = 1L;

	@Override
	protected String buildAuthUrl() {
		return null;
	}

	@Override
	protected String getStorageName() {
		return StorageNames.GOOGLEDRIVE;
	}
}
