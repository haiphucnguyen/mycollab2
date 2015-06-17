package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class LogoChangeViewImpl extends AbstractPageView implements LogoChangeView {
    public LogoChangeViewImpl() {
        with(new Label("Logo"));
    }
}
