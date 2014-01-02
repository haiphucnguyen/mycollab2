package com.esofthead.mycollab.mobile.module.user.event;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * Created by nghi on 1/2/14.
 */
public class UserEvent {
    public static class PlainLogin extends ApplicationEvent {

        public PlainLogin(Object source, Object data) {
            super(source, data);
        }
    }
}
