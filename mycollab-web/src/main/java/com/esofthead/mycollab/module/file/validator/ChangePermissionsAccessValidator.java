package com.esofthead.mycollab.module.file.validator;

import com.esofthead.mycollab.module.file.FileAccessDenyException;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;

/**
 * 
 */
public class ChangePermissionsAccessValidator extends DefaultAccessValidator {

    private static long[] CHANGE_PERMISSIONS = new long[] { IdentityPermission.FULL_CONTROL };

    public ChangePermissionsAccessValidator(String userSessionId,
            Content content) {
        super(userSessionId, content);
    }

    @Override
    public void validate() {
        super.validate();

        if (!hasRight(CHANGE_PERMISSIONS)) {
            throw new FileAccessDenyException("User "
                    + session.getRemoteUser()
                    + " has no right to view/edit permission of content "
                    + content.getPath());
        }
    }
}
