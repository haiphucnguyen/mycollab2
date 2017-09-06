package com.mycollab.core;

/**
 * This exception occur when MyCollab can not find any resource (Document, User,
 * etc)
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ResourceNotFoundException extends MyCollabException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable e) {
        super(e);
    }

    public ResourceNotFoundException() {
        super("");
    }

}
