package com.esofthead.mycollab.web;

import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.Application;
import com.vaadin.ui.Window.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCollabApplication extends Application {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory
            .getLogger(MyCollabApplication.class);
    private ViewManager viewManager;

    public MyCollabApplication() {
        super();
    }

    @Override
    public void init() {
        setTheme("mycollab");
        AppContext sessionData = new AppContext(this);
        this.setMainWindow(new MainWindowContainer());

        // Register it as a listener in the application context
        this.getContext().addTransactionListener(sessionData);
    }

    @Override
    public void close() {
        super.close();
        log.debug("Application is closed. Clean all resources");
        AppContext.clearSession();
        EventBus.getInstance().clear();
        AppContext.clearAllVariables();
    }
    
    

    @Override
    public void terminalError(com.vaadin.terminal.Terminal.ErrorEvent event) {
        getMainWindow().showNotification(
                "An internal error has occurred, please "
                + "contact the administrator!",
                Notification.TYPE_ERROR_MESSAGE);

        log.error("An uncaught exception occurred: ", event.getThrowable());
    }
}
