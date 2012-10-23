package com.esofthead.mycollab.vaadin.mvp.eventbus;

import java.util.EventObject;

/**
 * Serves as a parent for all application level events. It holds the source that triggered
 * the event and enforces each event implementation to provide an appropriate description for the event.
 */
public abstract class ApplicationEvent extends EventObject {

	private static final long serialVersionUID = 4160622600954681059L;

	public ApplicationEvent(Object source) {
		super(source);
	}

	/**
     * Returns the description of this event. This can be a dynamic description that changes between event instances of
     * the same type.
     *
     * @return The description of this event.
     */
    public abstract String getDescription();

	@Override
	public String toString() {
		return getDescription();
	}
    
    

}
