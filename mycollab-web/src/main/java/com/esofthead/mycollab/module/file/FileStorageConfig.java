package com.esofthead.mycollab.module.file;

import java.io.File;

public class FileStorageConfig {
	public static final File baseContentFolder;

	static {
		String userFolder = System.getProperty("user.home");
		baseContentFolder = new File(userFolder + "/.mycollab");
		baseContentFolder.mkdirs();

		File avatarFolder = new File(userFolder + "/.mycollab/avatar");
		avatarFolder.mkdirs();
	}

	public static File getAvatarFile(Integer accountId, String username,
			int size) {
		File userAvatarFile = (accountId == null) ? new File(baseContentFolder,
				"/avatar/" + username + "_" + size + ".png") : new File(
				baseContentFolder, accountId + "/avatar/" + username + "_"
						+ size + ".png");
		if (userAvatarFile.exists()) {
			return userAvatarFile;
		} else {
			return null;
		}
	}
}
