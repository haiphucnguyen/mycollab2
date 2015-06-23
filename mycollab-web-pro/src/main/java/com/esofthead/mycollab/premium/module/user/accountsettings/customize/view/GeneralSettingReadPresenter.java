package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class GeneralSettingReadPresenter extends AbstractPresenter<GeneralSettingReadView> {
    public GeneralSettingReadPresenter() {
        super(GeneralSettingReadView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        CustomizeContainer customizeContainer = (CustomizeContainer) container;
        customizeContainer.removeAllComponents();
        customizeContainer.addComponent(view);
        view.displayView();
    }
}
