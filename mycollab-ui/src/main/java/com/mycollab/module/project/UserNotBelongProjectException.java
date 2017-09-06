package com.mycollab.module.project;

import com.mycollab.core.SecureAccessException;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class UserNotBelongProjectException extends SecureAccessException {
    public UserNotBelongProjectException() {
        super("");
    }

    public UserNotBelongProjectException(String message) {
        super(message);
    }
}
