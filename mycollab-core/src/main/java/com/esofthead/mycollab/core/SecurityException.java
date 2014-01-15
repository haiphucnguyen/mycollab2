package com.esofthead.mycollab.core;

/**
 * Exception is thrown when user try to access prohibit resource
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class SecurityException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public SecurityException(final String message) {
		super(message);
	}

	public SecurityException(final Throwable e) {
		super(e);
	}

	public SecurityException(String message, Throwable e) {
		super(message, e);
	}

}
