package com.mycollab.module.file.domain.criteria;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.module.ecm.domain.ExternalDrive;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class FileSearchCriteria extends SearchCriteria {
    private static final long serialVersionUID = 1L;

    private String rootFolder;
    private String fileName;
    private String baseFolder;
    private String storageName;
    private ExternalDrive externalDrive;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public ExternalDrive getExternalDrive() {
        return externalDrive;
    }

    public void setExternalDrive(ExternalDrive externalDrive) {
        this.externalDrive = externalDrive;
    }
}
