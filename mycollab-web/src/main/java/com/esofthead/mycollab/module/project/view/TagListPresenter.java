package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class TagListPresenter extends AbstractPresenter<TagListView> {
    private static final long serialVersionUID = 1L;

    public TagListPresenter() {
        super(TagListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {

    }
}
