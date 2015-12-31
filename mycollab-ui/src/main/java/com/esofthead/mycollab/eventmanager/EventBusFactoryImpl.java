package com.esofthead.mycollab.eventmanager;

import com.esofthead.mycollab.vaadin.ui.MyCollabSession;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.esofthead.mycollab.vaadin.ui.MyCollabSession.EVENT_BUS_VAL;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class EventBusFactoryImpl extends EventBusFactory {
    private static final Logger LOG = LoggerFactory.getLogger(EventBusFactoryImpl.class);

    @Override
    public EventBus getInstanceInSession() {
        EventBus eventBus = (EventBus) MyCollabSession.getVariable(EVENT_BUS_VAL);
        LOG.debug("Event bus {}", eventBus);
        if (eventBus == null) {
            eventBus = new EventBus(new SubscriberEventBusExceptionHandler());
            MyCollabSession.putVariable(EVENT_BUS_VAL, eventBus);
            LOG.debug("Create new event bus {}", eventBus);
        }
        return eventBus;
    }

    private static class SubscriberEventBusExceptionHandler implements SubscriberExceptionHandler {

        @Override
        public void handleException(Throwable e, SubscriberExceptionContext context) {
            LOG.error("Error", e);
        }
    }
}