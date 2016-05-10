package com.esofthead.mycollab.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public interface IThemeCustomizeView extends PageView {
    void customizeTheme(AccountTheme accountTheme);
}
