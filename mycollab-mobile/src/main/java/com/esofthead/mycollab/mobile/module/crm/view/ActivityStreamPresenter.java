package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class ActivityStreamPresenter extends AbstractPresenter<ActivityStreamView> {

    public ActivityStreamPresenter() {
        super(ActivityStreamView.class);
    }

    @Override
    protected void onGo(NavigationManager container, ScreenData<?> data) {
        container.navigateTo(view.getWidget());
    }
}
