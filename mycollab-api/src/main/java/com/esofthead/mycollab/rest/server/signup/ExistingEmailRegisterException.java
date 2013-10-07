package com.esofthead.mycollab.rest.server.signup;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class ExistingEmailRegisterException extends UserInvalidInputException {

	public ExistingEmailRegisterException(final String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
