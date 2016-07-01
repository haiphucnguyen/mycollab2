package com.esofthead.mycollab.vaadin.web.ui.service;

import com.esofthead.mycollab.core.AbstractNotification;
import com.esofthead.mycollab.core.BroadcastMessage;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.web.DesktopApplication;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public abstract class AbstractBroadcastReceiverService implements BroadcastReceiverService {

    protected DesktopApplication myCollabApp;

    public void registerApp(DesktopApplication myCollabApp) {
        this.myCollabApp = myCollabApp;
    }

    @Override
    public void broadcast(BroadcastMessage message) {
        if (message.getWrapObj() instanceof AbstractNotification) {
            EventBusFactory.getInstance().post(new ShellEvent.NewNotification(this, message.getWrapObj()));
        } else {
            onBroadcast(message);
        }
    }

    abstract protected void onBroadcast(BroadcastMessage message);
}
