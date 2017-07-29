package com.mycollab.core;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class SecureAccessException extends MyCollabException {
    public SecureAccessException() {
        super("");
    }

    public SecureAccessException(String message) {
        super(message);
    }
}
