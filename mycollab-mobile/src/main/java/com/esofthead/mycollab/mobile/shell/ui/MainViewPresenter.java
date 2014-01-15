package com.esofthead.mycollab.mobile.shell.ui;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class MainViewPresenter extends AbstractPresenter<MainView> {

    public MainViewPresenter() {
        super(MainView.class);
    }

    @Override
    protected void onGo(NavigationManager navigationManager, ScreenData<?> data) {
        navigationManager.navigateTo(view.getWidget());
    }
}
