package com.esofthead.mycollab.module.file.validator;

import com.esofthead.mycollab.module.file.domain.Content;

public class AccessValidatorFactory {
    private static AccessValidator DEFAULT = new SimpleAccessValidator();

    public static AccessValidator getSimpleAccessValidator() {
        return DEFAULT;
    }

    /**
     * 
     * @param userSessionId
     * @param permissions
     * @return
     */
    public static AccessValidator getChangePermissionsAccessValidator(
            String userSessionId, Content content) {
        return new ChangePermissionsAccessValidator(userSessionId, content);
    }

    /**
     * 
     * @param userSessionId
     * @param permissions
     * @return
     */
    public static AccessValidator getCreateFileAndWriteDataAccessValidator(
            String userSessionId, Content content) {
        return new CreateFileAndWriteDataAccessValidator(userSessionId,
                content);
    }

    /**
     * 
     * @param userSessionId
     * @param permissions
     * @return
     */
    public static AccessValidator getDeleteAccessValidator(
            String userSessionId, Content content) {
        return new DeleteAccessValidator(userSessionId, content);
    }

    /**
     * 
     * @param userSessionId
     * @param permissions
     * @return
     */
    public static AccessValidator getListFoldersAndReadDataValidator(
            String userSessionId, Content content) {
        return new ListFoldersAndReadDataAccessValidator(userSessionId,
                content);
    }

    /**
     * 
     * @param userSessionId
     * @param permissions
     * @return
     */
    public static AccessValidator getReadPermissionsAccessValidator(
            String userSessionId, Content content) {
        return new ReadPermissionsAccessValidator(userSessionId, content);
    }
}
