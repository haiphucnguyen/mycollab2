package com.esofthead.mycollab.module.file;

import java.io.File;

import com.esofthead.mycollab.web.AppContext;

public class FileStorageConfig {
	public static final File baseContentFolder;

	static {
		String userFolder = System.getProperty("user.home");
		baseContentFolder = new File(userFolder + "/.mycollab");
		baseContentFolder.mkdirs();

		File avatarFolder = new File(userFolder + "/.mycollab/avatar");
		avatarFolder.mkdirs();
	}

	public static File getAvatarFile(String username, int size) {
		File userAvatarFile = new File(baseContentFolder,
				AppContext.getAccountId() + "/avatar/" + username + "_" + size
						+ ".png");
		if (userAvatarFile.exists()) {
			return userAvatarFile;
		} else {
			return null;
		}
	}
}
