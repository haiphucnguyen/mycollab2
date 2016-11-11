package com.mycollab.vaadin.mvp;

import com.vaadin.ui.Panel;

/**
 * @author MyCollab Ltd
 * @since 5.4.5
 */
public class AbstractSingleContainerPageView extends Panel implements PageView {

    public AbstractSingleContainerPageView() {
        super("");
        setSizeFull();
    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}
