package com.mycollab.ondemand.module.billing;

import com.mycollab.core.UserInvalidInputException;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ExistedSubDomainException extends UserInvalidInputException {
    private static final long serialVersionUID = 1L;

    public ExistedSubDomainException(String errorMsg) {
        super(errorMsg);
    }
}
