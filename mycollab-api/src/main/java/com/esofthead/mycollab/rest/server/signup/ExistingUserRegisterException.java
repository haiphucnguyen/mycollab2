package com.esofthead.mycollab.rest.server.signup;

import com.esofthead.mycollab.core.MyCollabException;

public class ExistingUserRegisterException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public ExistingUserRegisterException(final String message) {
		super(message);
	}
}
