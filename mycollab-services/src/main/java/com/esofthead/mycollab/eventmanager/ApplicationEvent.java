package com.esofthead.mycollab.eventmanager;

import java.util.EventObject;

/**
 * Serves as a parent for all application level events. It holds the source that
 * triggered the event and enforces each event implementation to provide an
 * appropriate description for the event.
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 4160622600954681059L;
    private String name;
    private Object data;

    public ApplicationEvent(Object source) {
        this(source, "", null);
    }

    public ApplicationEvent(Object source, String name) {
        this(source, name, null);
    }

    public ApplicationEvent(Object source, Object data) {
        this(source, "", data);
    }

    public ApplicationEvent(Object source, String name, Object data) {
        super(source);
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Object getData() {
        return data;
    }
}
