/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user;

import com.esofthead.mycollab.core.MyCollabException;

/**
 *
 * @author haiphucnguyen
 */
public class AuthenticationException extends MyCollabException {

    public AuthenticationException(String msg) {
        super(msg);
    }
}
