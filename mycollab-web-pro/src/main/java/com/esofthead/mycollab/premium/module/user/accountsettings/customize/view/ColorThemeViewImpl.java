package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class ColorThemeViewImpl extends AbstractPageView implements ColorThemeView {
    public ColorThemeViewImpl() {
        with(new Label("Theme"));
    }
}
