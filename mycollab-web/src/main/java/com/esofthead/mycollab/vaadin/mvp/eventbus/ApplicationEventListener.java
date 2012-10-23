package com.esofthead.mycollab.vaadin.mvp.eventbus;

import java.io.Serializable;
import java.util.EventListener;


/**
 * A listener that listens and is able to handle {@link ApplicationEvent application events}.
 */
public interface ApplicationEventListener<E extends ApplicationEvent> extends EventListener, Serializable {

	Class<? extends ApplicationEvent> getEventType();
	
    /**
     * Handles the given application event.
     *
     * @param event The event to handle.
     */
    void handle(E event);

}
