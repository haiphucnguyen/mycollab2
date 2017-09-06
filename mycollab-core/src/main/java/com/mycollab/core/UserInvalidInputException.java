package com.mycollab.core;

/**
 * This exception when user do some invalid action such as typing wrong
 * password, invalid input. Note that MyCollab catch this type exception to
 * recognize user mistake
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class UserInvalidInputException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public UserInvalidInputException(final String message) {
		super(message);
	}

	public UserInvalidInputException(final Throwable e) {
		super(e);
	}

	public UserInvalidInputException(String message, Throwable e) {
		super(message, e);
	}
}
