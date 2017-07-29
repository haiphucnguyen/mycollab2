package com.mycollab.module.user.accountsettings.view;

import com.mycollab.web.IDesktopModule;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public interface AccountModule extends IDesktopModule {
    void gotoSubView(String viewName);

    void gotoUserProfilePage();

}
