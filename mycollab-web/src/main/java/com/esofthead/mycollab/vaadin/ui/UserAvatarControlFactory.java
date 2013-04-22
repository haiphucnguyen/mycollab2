package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.module.file.FileStorageConfig;
import com.esofthead.mycollab.module.file.S3StorageConfig;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;

public class UserAvatarControlFactory {
	public static Embedded createUserAvatarEmbeddedControl(int accountId,
			String username, int size) {
		Embedded embedded = new Embedded(null);
		embedded.setSource(getResource(accountId, username, size));
		return embedded;

	}

	public static String getLink(int accountId, String username, int size) {
		if (username == null) {
			return "";
		}

		String link = "";

		if (StorageSetting.isFileStorage()) {
			link = ApplicationProperties
					.getProperty(ApplicationProperties.APP_URL)
					+ "avatar/"
					+ accountId + "/" + username + "/" + size;
		} else if (StorageSetting.isS3Storage()) {
			link = S3StorageConfig.getAvatarLink(accountId, username, size);
		}

		return link;
	}

	public static Resource getResource(int accountId, String username, int size) {
		Resource avatarRes = null;

		if (username == null) {
			return new ThemeResource("icons/default_user_avatar_" + size
					+ ".png");
		}

		if (StorageSetting.isFileStorage()) {
			File avatarFile = FileStorageConfig.getAvatarFile(accountId,
					username, size);
			if (avatarFile != null) {
				avatarRes = new FileResource(avatarFile,
						AppContext.getApplication());
			} else {
				avatarRes = new ThemeResource("icons/default_user_avatar_"
						+ size + ".png");
			}

		} else if (StorageSetting.isS3Storage()) {
			avatarRes = new ExternalResource(S3StorageConfig.getAvatarLink(
					accountId, username, size));
		}

		return avatarRes;
	}

	public static Button createUserAvatarLink(int accountId, String username,
			String fullName) {
		Button button = new Button();
		button.setIcon(getResource(accountId, username, 48));
		button.setStyleName("link");
		return button;
	}
}
