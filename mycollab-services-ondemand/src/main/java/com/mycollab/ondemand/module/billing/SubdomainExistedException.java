package com.mycollab.ondemand.module.billing;

import com.mycollab.core.UserInvalidInputException;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SubDomainExistedException extends UserInvalidInputException {
    private static final long serialVersionUID = 1L;

    public SubDomainExistedException(String errorMsg) {
        super(errorMsg);
    }
}
