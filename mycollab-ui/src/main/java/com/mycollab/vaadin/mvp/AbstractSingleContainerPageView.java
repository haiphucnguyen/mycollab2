package com.mycollab.vaadin.mvp;

import com.vaadin.ui.AbstractSingleComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.4.5
 */
public class AbstractSingleContainerPageView extends AbstractSingleComponentContainer implements PageView {

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}
