package com.esofthead.mycollab.module.file.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.ecm.StorageNames;

public abstract class GoogleDriveOAuthWindow extends
		CloudDriveIntegrationOAuthWindow {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(DropBoxOAuthWindow.class);

	public GoogleDriveOAuthWindow() {
		super("Add GoogleDrive");
	}

	@Override
	protected String buildAuthUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getStorageName() {
		return StorageNames.GOOGLEDRIVE;
	}
}
