package com.esofthead.mycollab.rest.server.signup;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class SubdomainExistedException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public SubdomainExistedException(String errorMsg) {
		super(errorMsg);
	}
}
