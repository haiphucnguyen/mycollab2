package com.mycollab.core;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BroadcastListener {
    /**
     * @param message
     */
    void broadcast(BroadcastMessage message);
}
