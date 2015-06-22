package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class LogoChangeViewImpl extends AbstractPageView implements LogoChangeView {
    public LogoChangeViewImpl() {
        this.setMargin(new MarginInfo(false, true, true, true));
        this.addStyleName("userInfoContainer");
    }

    @Override
    public void displayView() {
        removeAllComponents();
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true, false))
                .withWidth("100%").withStyleName(UIConstants.HEADER_VIEW);

        Label headerLbl = new Label("Logos for your site");
        headerLbl.addStyleName("h1");
        header.with(headerLbl);

        addComponent(header);


    }
}
