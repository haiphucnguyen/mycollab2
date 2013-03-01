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
}
