package com.mycollab.common;

import com.mycollab.core.MyCollabException;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class InvalidTokenException extends MyCollabException {
    private static final long serialVersionUID = 1L;

    public InvalidTokenException(String message) {
        super(message);
    }
}
