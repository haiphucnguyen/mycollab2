package com.esofthead.mycollab.module.file;

import com.esofthead.mycollab.common.ApplicationProperties;

public class StorageSetting {
	public static final String FILE_STORAGE_SYSTEM = "file";

	public static final String S3_STORAGE_SYSTEM = "s3";

	public static boolean isFileStorage() {
		String storageSystem = ApplicationProperties.getProperty(
				ApplicationProperties.STORAGE_SYSTEM, "file");
		return StorageSetting.FILE_STORAGE_SYSTEM.equals(storageSystem.trim());
	}

	public static boolean isS3Storage() {
		String storageSystem = ApplicationProperties.getProperty(
				ApplicationProperties.STORAGE_SYSTEM, "file");
		return StorageSetting.S3_STORAGE_SYSTEM.equals(storageSystem.trim());
	}
}
