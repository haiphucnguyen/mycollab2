package com.esofthead.mycollab.module.file.validator;

import com.esofthead.mycollab.module.file.FileAccessDenyException;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;

public class ListFoldersAndReadDataAccessValidator extends
        DefaultAccessValidator {
    // permissions to list folders and read data
    public static long[] LIST_FOLDERS_READ_DATA = new long[] {
            IdentityPermission.FULL_CONTROL, IdentityPermission.MODIFY,
            IdentityPermission.READ_EXECUTE, IdentityPermission.LIST_FOLDERS,
            IdentityPermission.READ };

    public ListFoldersAndReadDataAccessValidator(String userSessionId,
            Content content) {
        super(userSessionId, content);
    }

    @Override
    public void validate() {
        super.validate();

        if (!hasRight(LIST_FOLDERS_READ_DATA)) {
            throw new FileAccessDenyException("User "
                    + session.getRemoteUser()
                    + " has no right to browse folder " + content.getPath());
        }
    }

}
