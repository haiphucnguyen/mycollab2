package com.mycollab.core;

/**
 * Generic exception of MyCollab. All exceptions occurs in MyCollab should be
 * wrapped into this exception type.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MyCollabException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyCollabException(String message) {
        super(message);
    }

    public MyCollabException(Throwable e) {
        super(e);
    }

    public MyCollabException(String message, Throwable e) {
        super(message, e);
    }
}
