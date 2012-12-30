/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

/**
 *
 * @author haiphucnguyen
 */
public class UserEvent {

    public static class PlainLogin extends ApplicationEvent {

        public PlainLogin(Object source, Object data) {
            super(source, data);
        }
    }
}
