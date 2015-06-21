package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class GeneralSettingAddPresenter extends AbstractPresenter<GeneralSettingAddView> {
    public GeneralSettingAddPresenter() {
        super(GeneralSettingAddView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        GeneralSettingContainer customizeContainer = (GeneralSettingContainer) container;
        customizeContainer.removeAllComponents();
        customizeContainer.addComponent(view);
        view.displayView();
    }
}
