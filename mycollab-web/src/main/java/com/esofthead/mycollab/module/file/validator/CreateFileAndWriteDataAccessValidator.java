package com.esofthead.mycollab.module.file.validator;

import com.esofthead.mycollab.module.file.FileAccessDenyException;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;

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
