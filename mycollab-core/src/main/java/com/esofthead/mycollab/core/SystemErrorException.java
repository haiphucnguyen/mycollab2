package com.esofthead.mycollab.core;

public class SystemErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SystemErrorException(final String message) {
		super(message);
	}

	public SystemErrorException(final Throwable e) {
		super(e);
	}

	public SystemErrorException(String message, Throwable e) {
		super(message, e);
	}
}
