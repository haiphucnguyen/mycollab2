package com.mycollab.core;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class InvalidPasswordException extends UserInvalidInputException {
    private static final long serialVersionUID = 1L;

    public InvalidPasswordException(String message) {
        super(message);
    }

}
