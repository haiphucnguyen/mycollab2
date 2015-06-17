package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class GeneralSettingViewImpl extends AbstractPageView implements GeneralSettingView {

    public GeneralSettingViewImpl() {
        super();
        this.setMargin(new MarginInfo(false, true, true, true));
        this.addStyleName("userInfoContainer");
    }
}
