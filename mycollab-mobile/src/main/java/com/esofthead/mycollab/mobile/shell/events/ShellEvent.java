package com.esofthead.mycollab.mobile.shell.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class ShellEvent {

    public static class GotoMainPage extends ApplicationEvent {

        public GotoMainPage(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoCrmModule extends ApplicationEvent {

        public GotoCrmModule(Object source, Object data) {
            super(source, data);
        }
    }
}
