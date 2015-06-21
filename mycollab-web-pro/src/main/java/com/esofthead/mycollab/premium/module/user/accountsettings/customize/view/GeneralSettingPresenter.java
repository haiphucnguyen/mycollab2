package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class GeneralSettingPresenter extends AbstractPresenter<GeneralSettingContainer> {
    public GeneralSettingPresenter() {
        super(GeneralSettingContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AbstractPresenter presenter;
        if (data instanceof SettingExtScreenData.GeneralSettingRead || data == null) {
            presenter = PresenterResolver.getPresenter(GeneralSettingReadPresenter.class);
        } else if (data instanceof SettingExtScreenData.GeneralSettingEdit) {
            presenter = PresenterResolver.getPresenter(GeneralSettingAddPresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view, data);
    }
}
