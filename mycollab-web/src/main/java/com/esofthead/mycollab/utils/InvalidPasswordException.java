package com.esofthead.mycollab.utils;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class InvalidPasswordException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
		super(message);
	}

}
