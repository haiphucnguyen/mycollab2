package com.esofthead.mycollab.ondemand.module.billing;

import com.esofthead.mycollab.core.UserInvalidInputException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ExistingUserRegisterException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public ExistingUserRegisterException(String message) {
		super(message);
	}
}
