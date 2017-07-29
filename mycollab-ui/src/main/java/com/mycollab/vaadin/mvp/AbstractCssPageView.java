package com.mycollab.vaadin.mvp;

import com.vaadin.ui.CssLayout;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class AbstractCssPageView extends CssLayout implements PageView, Serializable {
    public AbstractCssPageView() {
        this.setSizeFull();
    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}
