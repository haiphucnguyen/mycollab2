package com.esofthead.mycollab.core;


public class CannotCleanupResourceException extends EngroupException {
    private static final long serialVersionUID = 1L;

    public CannotCleanupResourceException(String message, Throwable e) {
        super(message, e);
    }
}
