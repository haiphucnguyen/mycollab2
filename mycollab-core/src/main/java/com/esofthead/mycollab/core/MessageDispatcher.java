package com.esofthead.mycollab.core;

public interface MessageDispatcher {

    /**
     * 
     * @param destination
     * @param object
     */
    void dispatchObject(String destination, Object object);
}
