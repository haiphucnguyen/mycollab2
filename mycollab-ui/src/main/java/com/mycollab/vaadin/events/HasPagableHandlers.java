package com.mycollab.vaadin.events;

import java.io.Serializable;

/**
 * Collection contains handlers of do paging in list or table
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface HasPagableHandlers extends Serializable {
    /**
     * Add page handler
     *
     * @param handler page handler
     */
    void addPageableHandler(PageableHandler handler);
}
