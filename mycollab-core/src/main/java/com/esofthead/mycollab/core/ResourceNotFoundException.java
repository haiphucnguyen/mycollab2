package com.esofthead.mycollab.core;

public class ResourceNotFoundException extends UserInvalidInputException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super("");
	}

	public ResourceNotFoundException(final Throwable e) {
		super(e);
	}

	public ResourceNotFoundException(String message, Throwable e) {
		super(message, e);
	}

}
