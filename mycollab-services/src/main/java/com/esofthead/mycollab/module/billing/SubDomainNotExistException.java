package com.esofthead.mycollab.module.billing;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class SubDomainNotExistException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public SubDomainNotExistException(String errorMsg) {
		super(errorMsg);
	}
}
