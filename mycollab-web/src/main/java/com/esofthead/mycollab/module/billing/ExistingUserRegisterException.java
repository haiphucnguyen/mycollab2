package com.esofthead.mycollab.module.billing;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class ExistingUserRegisterException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public ExistingUserRegisterException(String message) {
		super(message);
	}
}
