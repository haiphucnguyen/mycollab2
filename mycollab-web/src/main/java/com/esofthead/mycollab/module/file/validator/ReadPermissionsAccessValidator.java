package com.esofthead.mycollab.module.file.validator;

import com.esofthead.mycollab.module.file.FileAccessDenyException;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;

public class ReadPermissionsAccessValidator extends DefaultAccessValidator {

    public static long[] READ_PERMISSIONS = new long[] {
            IdentityPermission.FULL_CONTROL, IdentityPermission.MODIFY,
            IdentityPermission.READ_EXECUTE, IdentityPermission.LIST_FOLDERS,
            IdentityPermission.READ, IdentityPermission.WRITE };

    public ReadPermissionsAccessValidator(String userSessionId, Content content) {
        super(userSessionId, content);
    }

    @Override
    public void validate() {
        super.validate();

        if (!hasRight(READ_PERMISSIONS)) {
            throw new FileAccessDenyException("User "
                    + session.getRemoteUser()
                    + " has no right to read permission of content "
                    + content.getPath());
        }
    }
}
