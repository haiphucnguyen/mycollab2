package com.esofthead.mycollab.configuration;

import java.io.File;

public class FileStorageConfiguration implements StorageConfiguration {

	public static File baseContentFolder;

	static {
		String userFolder = System.getProperty("user.home");
		baseContentFolder = new File(userFolder + "/.mycollab");
		baseContentFolder.mkdirs();
	}

	private FileStorageConfiguration() {
		File avatarFolder = new File(baseContentFolder, "avatar");
		avatarFolder.mkdirs();
	}

	public static FileStorageConfiguration build() {
		return new FileStorageConfiguration();
	}

	@Override
	public String generateAvatarPath(String userAvatarId, int size) {
		return SiteConfiguration.getSiteUrl("app") + "avatar/" + userAvatarId
				+ "/" + size;
	}

	@Override
	public String generateResourcePath(String documentPath) {
		return baseContentFolder.getPath() + "/" + documentPath;
	}

	public File getAvatarFile(String username, int size) {
		File userAvatarFile = new File(baseContentFolder, "/avatar/" + username
				+ "_" + size + ".png");
		if (userAvatarFile.exists()) {
			return userAvatarFile;
		} else {
			return null;
		}
	}

}
