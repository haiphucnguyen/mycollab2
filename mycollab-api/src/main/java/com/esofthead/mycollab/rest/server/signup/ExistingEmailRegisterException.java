package com.esofthead.mycollab.rest.server.signup;

import com.esofthead.mycollab.core.MyCollabException;

public class ExistingEmailRegisterException extends MyCollabException {

	public ExistingEmailRegisterException(final String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
