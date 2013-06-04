package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout implements View,
        Serializable {

    private static Logger log = LoggerFactory.getLogger(AbstractView.class);
    public static String SAVE_ACTION = "Save";
    public static String SAVE_AND_NEW_ACTION = "Save & New";
    public static String EDIT_ACTION = "Edit";
    public static String CANCEL_ACTION = "Cancel";
    public static String DELETE_ACTION = "Delete";
    public static String CLONE_ACTION = "Clone";
    private Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> map;
    protected ViewState viewState;

    public ViewState getViewState() {
        return viewState;
    }

    public void setViewState(ViewState viewState) {
        this.viewState = viewState;
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ApplicationEventListener<? extends ApplicationEvent> listener) {
        if (map == null) {
            map = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
        }

        Set<ApplicationEventListener<?>> listenerSet = map.get(listener
                .getEventType());
        if (listenerSet == null) {
            listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
            map.put(listener.getEventType(), listenerSet);
        }

        listenerSet.add(listener);
    }

    protected void fireEvent(ApplicationEvent event) {

        log.debug("Fire event: {}", event);

        Class<? extends ApplicationEvent> eventType = event.getClass();

        Set<ApplicationEventListener<?>> eventSet = map.get(eventType);
        if (eventSet != null) {
            Iterator<ApplicationEventListener<?>> listenerSet = map.get(eventType).iterator();

            while (listenerSet.hasNext()) {
                ApplicationEventListener<?> listener = listenerSet.next();
                @SuppressWarnings("unchecked")
                ApplicationEventListener<ApplicationEvent> l = (ApplicationEventListener<ApplicationEvent>) listener;
                l.handle(event);
            }
        } else {
            log.error("No listener is registered for event type " + eventType);
        }


    }
}
