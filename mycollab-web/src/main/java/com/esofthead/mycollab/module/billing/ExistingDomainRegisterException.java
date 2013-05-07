package com.esofthead.mycollab.module.billing;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class ExistingDomainRegisterException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public ExistingDomainRegisterException(String message) {
		super(message);
	}

}
