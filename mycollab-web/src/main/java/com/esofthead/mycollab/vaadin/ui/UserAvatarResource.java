package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

import com.esofthead.mycollab.module.file.FileStorageConfig;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

public class UserAvatarResource extends Embedded {
	private static final long serialVersionUID = 1L;

	public UserAvatarResource(String username, int size) {
		super();
		Resource avatarRes = null;

		if (StorageSetting.isFileStorage()) {
			File avatarFile = FileStorageConfig.getAvatarFile(username, size);
			if (avatarFile != null) {
				avatarRes = new FileResource(avatarFile,
						AppContext.getApplication());
			} else {
				avatarRes = new ThemeResource("icons/default_user_avatar_" + size + ".png");
			}
		} else if (StorageSetting.isS3Storage()) {

		}

		this.setSource(avatarRes);
	}

}
