package com.esofthead.mycollab.community.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.accountsettings.customize.view.IThemeCustomizePresenter;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.IThemeCustomizeView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class ThemeCustomizePresenter extends AbstractPresenter<IThemeCustomizeView> implements IThemeCustomizePresenter {
    public ThemeCustomizePresenter() {
        super(IThemeCustomizeView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {

    }
}
