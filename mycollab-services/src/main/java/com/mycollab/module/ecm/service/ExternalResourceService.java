package com.mycollab.module.ecm.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.ecm.domain.*;

import java.io.InputStream;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@IgnoreCacheClass
public interface ExternalResourceService extends IService {
    /**
     * @param drive
     * @param path
     * @return
     */
    List<Resource> getResources(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param path
     * @return
     */
    List<ExternalFolder> getSubFolders(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param path
     * @return
     */
    Resource getCurrentResourceByPath(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param childPath
     * @return
     */
    Folder getParentResourceFolder(ExternalDrive drive, String childPath);

    /**
     * @param drive
     * @param path
     * @return
     */
    Folder createNewFolder(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param content
     * @param in
     */
    void saveContent(ExternalDrive drive, Content content, InputStream in);

    /**
     * @param drive
     * @param oldPath
     * @param newPath
     */
    void rename(ExternalDrive drive, String oldPath, String newPath);

    /**
     * @param drive
     * @param path
     */
    void deleteResource(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param path
     * @return
     */
    InputStream download(ExternalDrive drive, String path);

    /**
     * @param drive
     * @param fromPath
     * @param toPath
     */
    void move(ExternalDrive drive, String fromPath, String toPath);
}
