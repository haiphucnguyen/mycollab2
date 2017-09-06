package com.mycollab.module.page.service;

import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.page.domain.Folder;
import com.mycollab.module.page.domain.Page;
import com.mycollab.module.page.domain.PageResource;
import com.mycollab.module.page.domain.PageVersion;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public interface PageService extends IService {
    /**
     * @param page
     * @param createdUser
     */
    void savePage(Page page, String createdUser);

    /**
     * @param path
     * @param requestedUser
     * @return
     */
    Page getPage(String path, String requestedUser);

    /**
     * @param path
     * @return
     */
    Folder getFolder(String path);

    /**
     * @param path
     * @return
     */
    List<PageVersion> getPageVersions(String path);

    /**
     * @param path
     * @param versionName
     * @return
     */
    Page getPageByVersion(String path, String versionName);

    /**
     * @param path
     * @param versionName
     * @return the restore page
     */
    Page restorePage(String path, String versionName);

    /**
     * @param folder
     * @param createdUser
     */
    void createFolder(Folder folder, String createdUser);

    /**
     * @param path
     * @return
     */
    List<Page> getPages(String path, String requestedUser);

    /**
     * @param path
     * @param requestedUser
     * @return
     */
    List<PageResource> getResources(String path, String requestedUser);

    /**
     * @param path
     */
    void removeResource(String path);
}
