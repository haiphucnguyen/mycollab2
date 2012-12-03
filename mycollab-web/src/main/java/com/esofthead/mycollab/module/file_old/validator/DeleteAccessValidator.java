package com.esofthead.mycollab.module.file_old.validator;

import com.esofthead.mycollab.module.file_old.FileAccessDenyException;
import com.esofthead.mycollab.module.file_old.domain.Content;
import com.esofthead.mycollab.module.file_old.domain.IdentityPermission;

public class DeleteAccessValidator extends DefaultAccessValidator {
    // permissions to delete
    public static long[] DELETE = new long[] { IdentityPermission.FULL_CONTROL,
            IdentityPermission.MODIFY };

    public DeleteAccessValidator(String userSessionId, Content content) {
        super(userSessionId, content);
    }

    @Override
    public void validate() {
        super.validate();
        if (!hasRight(DELETE)) {
            throw new FileAccessDenyException("User "
                    + session.getRemoteUser()
                    + " has no right to remove content " + content.getPath());
        }

    }

}
