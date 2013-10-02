/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class UserEvent {

    
	public static class PlainLogin extends ApplicationEvent {

        public PlainLogin(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class Search extends ApplicationEvent {

        public Search(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoList extends ApplicationEvent {

        public GotoList(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoAdd extends ApplicationEvent {

        public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoRead extends ApplicationEvent {

        public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoEdit extends ApplicationEvent {

        public GotoEdit(Object source, Object data) {
            super(source, data);
        }
    }
}
