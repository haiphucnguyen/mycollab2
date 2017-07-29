package com.mycollab.module.billing;

import com.mycollab.core.UserInvalidInputException;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SubDomainNotExistException extends UserInvalidInputException {
    private static final long serialVersionUID = 1L;

    public SubDomainNotExistException(String errorMsg) {
        super(errorMsg);
    }
}
