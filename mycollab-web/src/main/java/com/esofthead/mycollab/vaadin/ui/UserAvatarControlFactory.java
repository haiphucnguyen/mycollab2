package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

import com.esofthead.mycollab.module.file.FileStorageConfig;
import com.esofthead.mycollab.module.file.S3StorageConfig;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;

public class UserAvatarControlFactory {
	public static Embedded createUserAvatarEmbeddedControl(String username,
			int size) {
		Embedded embedded = new Embedded(null);
		embedded.setSource(getResource(username, size));
		return embedded;

	}

	public static String getLink(String username, int size) {
		if (username == null) {
			return "";
		}

		String link = "";

		if (StorageSetting.isFileStorage()) {
			link = AppContext.getSiteUrl() + "avatar/" + username + "/" + size;
		} else if (StorageSetting.isS3Storage()) {
			link = S3StorageConfig.getAvatarLink(username, size);
		}

		return link;
	}

	public static Resource getResource(String username, int size) {
		Resource avatarRes = null;

		if (username == null) {
			return MyCollabResource.newResource("icons/default_user_avatar_"
					+ size + ".png");
		}

		if (StorageSetting.isFileStorage()) {
			File avatarFile = FileStorageConfig.getAvatarFile(username, size);
			if (avatarFile != null) {
				avatarRes = new FileResource(avatarFile,
						AppContext.getApplication());
			} else {
				avatarRes = MyCollabResource
						.newResource("icons/default_user_avatar_" + size
								+ ".png");
			}

		} else if (StorageSetting.isS3Storage()) {
			avatarRes = new ExternalResource(S3StorageConfig.getAvatarLink(
					username, size));
		}

		return avatarRes;
	}

	public static Button createUserAvatarLink(String username, String fullName) {
		Button button = new Button();
		button.setIcon(getResource(username, 48));
		button.setStyleName("link");
		return button;
	}
}
