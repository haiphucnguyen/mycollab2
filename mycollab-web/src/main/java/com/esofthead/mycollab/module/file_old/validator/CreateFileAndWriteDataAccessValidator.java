package com.esofthead.mycollab.module.file_old.validator;

import com.esofthead.mycollab.module.file_old.FileAccessDenyException;
import com.esofthead.mycollab.module.file_old.domain.Content;
import com.esofthead.mycollab.module.file_old.domain.IdentityPermission;

public class CreateFileAndWriteDataAccessValidator extends
        DefaultAccessValidator {
    // permissions to create files and write meta data
    public static long[] CREATE_FILES_WRITE_DATA = new long[] {
            IdentityPermission.FULL_CONTROL, IdentityPermission.MODIFY,
            IdentityPermission.WRITE };

    public CreateFileAndWriteDataAccessValidator(String userSessionId,
            Content content) {
        super(userSessionId, content);
    }

    @Override
    public void validate() {
        super.validate();
        if (!hasRight(CREATE_FILES_WRITE_DATA)) {
            throw new FileAccessDenyException("User "
                    + session.getRemoteUser()
                    + " has no right to create or write content with path "
                    + content.getPath());
        }
    }
}
