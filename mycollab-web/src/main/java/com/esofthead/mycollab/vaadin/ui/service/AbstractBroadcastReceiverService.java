package com.esofthead.mycollab.vaadin.ui.service;

import com.esofthead.mycollab.core.AbstractNotification;
import com.esofthead.mycollab.core.BroadcastMessage;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.shell.events.ShellEvent;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public abstract class AbstractBroadcastReceiverService implements BroadcastReceiverService {
    @Override
    public void broadcast(BroadcastMessage message) {
        if (message.getWrapObj() instanceof AbstractNotification) {
            EventBusFactory.getInstance().post(new ShellEvent.NewNotification(this, message.getWrapObj()));
        } else {

        }
    }

    abstract protected void onBroadcast(BroadcastMessage message);
}
