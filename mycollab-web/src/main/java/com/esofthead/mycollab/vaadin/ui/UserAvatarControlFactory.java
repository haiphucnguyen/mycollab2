package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

import com.esofthead.mycollab.configuration.FileStorageConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;

public class UserAvatarControlFactory {
	public static Embedded createUserAvatarEmbeddedComponent(String avatarId,
			int size) {
		Embedded embedded = new Embedded(null);
		embedded.setSource(createAvatarResource(avatarId, size));
		return embedded;

	}

	public static String getAvatarLink(String userAvatarId, int size) {
		if (userAvatarId == null) {
			return "";
		}

		String link = SiteConfiguration.getStorageConfiguration()
				.generateAvatarPath(userAvatarId, size);

		return link;
	}

	public static Resource createAvatarResource(String avatarId, int size) {
		Resource avatarRes = null;

		if (avatarId == null) {
			return MyCollabResource.newResource("icons/default_user_avatar_"
					+ size + ".png");
		}

		if (SiteConfiguration.isSupportFileStorage()) {
			FileStorageConfiguration fileStorageConfiguration = (FileStorageConfiguration) SiteConfiguration
					.getStorageConfiguration();
			File avatarFile = fileStorageConfiguration.getAvatarFile(avatarId,
					size);
			if (avatarFile != null) {
				avatarRes = new FileResource(avatarFile,
						AppContext.getApplication());
			} else {
				avatarRes = MyCollabResource
						.newResource("icons/default_user_avatar_" + size
								+ ".png");
			}

		} else if (SiteConfiguration.isSupportS3Storage()) {
			avatarRes = new ExternalResource(SiteConfiguration
					.getStorageConfiguration().generateAvatarPath(avatarId,
							size));
		}

		return avatarRes;
	}

	public static Button createUserAvatarButtonLink(String userAvatarId,
			String fullName) {
		Button button = new Button();
		button.setIcon(createAvatarResource(userAvatarId, 48));
		button.setStyleName("link");
		return button;
	}
}
