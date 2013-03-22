package com.esofthead.mycollab.shell.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ShellEvent {

    public static class GotoMainPage extends ApplicationEvent {

        public GotoMainPage(Object source, Object data) {
            super(source, data);
        }
    }

    public static class LogOut extends ApplicationEvent {

        public LogOut(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoProjectModule extends ApplicationEvent {

        public GotoProjectModule(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoCrmModule extends ApplicationEvent {

        public GotoCrmModule(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoUserAccountModule extends ApplicationEvent {

        public GotoUserAccountModule(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoConsolePage extends ApplicationEvent {

        public GotoConsolePage(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoSignupPage extends ApplicationEvent {

        public GotoSignupPage(Object source, Object data) {
            super(source, data);
        }
    }
    
    public static class GotoForgotPasswordPage extends ApplicationEvent {

        public GotoForgotPasswordPage(Object source, Object data) {
            super(source, data);
        }
    }
}
