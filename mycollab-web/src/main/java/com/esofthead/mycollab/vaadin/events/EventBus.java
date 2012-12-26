package com.esofthead.mycollab.vaadin.events;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class EventBus implements Serializable {

    private Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> map = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
    private final Logger log = LoggerFactory.getLogger(EventBus.class);
    private static EventBus instance = new EventBus();

    public static EventBus getInstance() {
        return instance;
    }

    public void addListener(ApplicationEventListener<?> listener) {

        Set<ApplicationEventListener<?>> listenerSet = map.get(listener
                .getEventType());
        if (listenerSet == null) {
            listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
            map.put(listener.getEventType(), listenerSet);
        }

        listenerSet.add(listener);

        log.debug("Added event bus listener: {}", listener.getClass());

    }

    public void removeListener(ApplicationEventListener<?> listener) {
        Set<ApplicationEventListener<?>> listenerSet = map.get(listener
                .getEventType());
        listenerSet.remove(listener);
    }

    public void clear() {
        map.clear();
    }

    public void fireEvent(ApplicationEvent event) {

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

    @Override
    public String toString() {

        StringBuilder strb = new StringBuilder(
                "Registered Listener on EventBus: \r\n");

        for (Entry<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> entry : map
                .entrySet()) {
            strb.append(entry.getKey()).append(": ").append(entry.getValue())
                    .append("\r\n");
        }

        return strb.toString();
    }
}
