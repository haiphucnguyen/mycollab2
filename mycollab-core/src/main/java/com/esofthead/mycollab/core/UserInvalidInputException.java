/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.core;

/**
 *
 * @author haiphucnguyen
 */
public class UserInvalidInputException extends MyCollabException {

    public UserInvalidInputException(final String message) {
        super(message);
    }

    public UserInvalidInputException(final Throwable e) {
        super(e);
    }

    public UserInvalidInputException(String message, Throwable e) {
        super(message, e);
    }
}
