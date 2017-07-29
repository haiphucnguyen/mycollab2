package com.mycollab.core;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public class DebugException extends MyCollabException {
    public DebugException(String message) {
        super(message);
    }

    public DebugException(String message, Throwable e) {
        super(message, e);
    }
}
