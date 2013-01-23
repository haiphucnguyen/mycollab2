package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.view.UserDashboardView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class MyFeedsPresenter extends AbstractPresenter<MyFeedsView> {

    private static final long serialVersionUID = 1L;

    public MyFeedsPresenter() {
        super(MyFeedsView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        UserDashboardView projectViewContainer = (UserDashboardView) container;
        projectViewContainer.gotoSubView("My Feeds");
        view.displayFeeds();

    }
}
