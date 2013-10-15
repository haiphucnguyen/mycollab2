package com.esofthead.mycollab.utils;

import com.esofthead.mycollab.core.MyCollabException;

public class InvalidPasswordException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
		super(message);
	}

}
