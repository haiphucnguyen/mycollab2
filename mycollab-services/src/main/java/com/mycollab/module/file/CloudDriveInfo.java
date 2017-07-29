package com.mycollab.module.file;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CloudDriveInfo {
    private String storageName;

    private String accessToken;

    private String folderName;

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

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
