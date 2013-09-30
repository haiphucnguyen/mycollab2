package com.esofthead.mycollab.configuration;

public interface StorageConfiguration {
	public static final String FILE_STORAGE_SYSTEM = "file";

	public static final String S3_STORAGE_SYSTEM = "s3";

	public String generateAvatarPath(String userAvatarId, int size);

	public String generateResourcePath(String documentPath);
}
