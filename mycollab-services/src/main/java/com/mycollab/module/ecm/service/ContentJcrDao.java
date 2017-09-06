package com.mycollab.module.ecm.service;

import com.mycollab.module.ecm.domain.Content;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.domain.Resource;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ContentJcrDao {

    void saveContent(Content content, String createdUser);

    void createFolder(Folder folder, String createdUser);

    void rename(String oldPath, String newPath);

    Resource getResource(String path);

    void removeResource(String path);

    List<Resource> getResources(String path);

    List<Content> getContents(String path);

    List<Folder> getSubFolders(String path);

    List<Resource> searchResourcesByName(String baseFolderPath, String resourceName);

    void moveResource(String oldPath, String destinationPath);
}
