package com.esofthead.mycollab.module.file;

public class CloudDriveInfo {
	private String storageName;

	private String accessToken;

	public CloudDriveInfo(String storageName, String accessToken) {
		this.storageName = storageName;
		this.accessToken = accessToken;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
