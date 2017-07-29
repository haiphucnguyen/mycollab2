package com.mycollab.vaadin.mvp;

import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class AbstractVerticalPageView extends MVerticalLayout implements PageView {

    public AbstractVerticalPageView() {
        this.withSpacing(false).withMargin(false);
    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}
