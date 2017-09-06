package com.mycollab.mobile.ui;

import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.mvp.ViewEvent;
import com.vaadin.ui.VerticalLayout;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class AbstractMobileMainView extends VerticalLayout implements PageView, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}