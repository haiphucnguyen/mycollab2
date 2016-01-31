package com.esofthead.mycollab.mobile.mvp.view;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class NotPresentPresenter extends AbstractPresenter<NotPresenterView> {
    public NotPresentPresenter() {
        super(NotPresenterView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {

    }
}
