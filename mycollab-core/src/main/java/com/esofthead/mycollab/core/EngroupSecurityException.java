package com.esofthead.mycollab.core;


public class EngroupSecurityException extends EngroupException {
    private static final long serialVersionUID = 1L;

    public EngroupSecurityException(String message, Throwable e) {
        super(message, e);
    }

    public EngroupSecurityException(Throwable e) {
        super(e);
    }

    public EngroupSecurityException(String message) {
        super(message);
    }
}
