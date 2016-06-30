package com.esofthead.mycollab.core;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BroadcastListener {
    /**
     * @param notification
     */
    void broadcast(BroadcastMessage notification);
}
