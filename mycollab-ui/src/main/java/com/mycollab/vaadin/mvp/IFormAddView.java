package com.mycollab.vaadin.mvp;

import com.mycollab.vaadin.events.HasEditFormHandlers;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface IFormAddView<B> extends PageView {
    /**
     * @param item
     */
    void editItem(B item);

    /**
     * @return
     */
    HasEditFormHandlers<B> getEditFormHandlers();
}
