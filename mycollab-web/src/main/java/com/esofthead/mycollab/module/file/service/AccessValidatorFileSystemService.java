package com.esofthead.mycollab.module.file.service;

import java.util.Collection;

import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.ContentSearchResult;
import com.esofthead.mycollab.module.file.domain.FileItem;
import com.esofthead.mycollab.module.file.domain.Folder;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;
import com.esofthead.mycollab.module.file.domain.SimpleFile;
import com.esofthead.mycollab.module.file.domain.criteria.ContentSearchCriteria;
import com.esofthead.mycollab.module.file.validator.AccessValidator;

public interface AccessValidatorFileSystemService extends ContentService {

    /**
     * 
     * @param accessValidator
     * @param content
     * @param isTrack
     */
    void saveWithAccessValidator(AccessValidator accessValidator,
            Content content, boolean isTrack);

    /**
     * 
     * @param accessValidator
     * @param content
     * @param isTrack
     */
    void updateWithAccessValidator(AccessValidator accessValidator,
            Content content, boolean isTrack);

    /**
     * 
     * @param accessValidator
     * @param content
     * @param isTrack
     */
    void removeWithAccessValidator(AccessValidator accessValidator,
            Content content, boolean isTrack);

    /**
     * 
     * @param criteria
     * @return
     */
    Collection<ContentSearchResult> findByCriteria(String userSessionId,
            ContentSearchCriteria criteria);


    /**
     * 
     * @param filePath
     * @param version
     * @return
     */
    FileItem getFileByVersion(AccessValidator accessValidator, Content content,
            String version);

    /**
     * 
     * @param filePath
     * @return
     */
    Collection<Content> listContents(AccessValidator accessValidator,
            Folder folder);

    /**
     * 
     * @param accessValidator
     * @param file
     */
    void updateFileMetaData(AccessValidator accessValidator, SimpleFile file);

    /**
     * 
     * @param path
     * @param permissions
     */
    void savePermissions(AccessValidator accessValidator, Folder folder,
            Collection<IdentityPermission> permissions);

    /**
     * 
     * @param path
     * @return
     */
    Collection<Content> getSubContents(final String path);
}
