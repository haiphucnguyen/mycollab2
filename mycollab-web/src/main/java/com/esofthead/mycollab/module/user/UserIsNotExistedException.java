package com.esofthead.mycollab.module.user;

import com.esofthead.mycollab.core.MyCollabException;

public class UserIsNotExistedException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public UserIsNotExistedException(String message) {
		super(message);
	}

}
