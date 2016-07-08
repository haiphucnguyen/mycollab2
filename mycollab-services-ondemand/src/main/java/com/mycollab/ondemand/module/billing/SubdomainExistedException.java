package com.mycollab.ondemand.module.billing;

import com.mycollab.core.UserInvalidInputException;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SubdomainExistedException extends UserInvalidInputException {
    private static final long serialVersionUID = 1L;

    public SubdomainExistedException(String errorMsg) {
        super(errorMsg);
    }
}