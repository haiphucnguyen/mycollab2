package com.mycollab.premium.module.user.accountsettings.billing.view;

import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.license.LicenseInfo;
import com.mycollab.license.service.LicenseResolver;
import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.premium.shell.view.components.BuyPremiumSoftwareWindow;
import com.mycollab.premium.shell.view.components.LicenseActivationWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractSingleContainerPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class BillingContainer extends AbstractSingleContainerPageView implements IBillingContainer {
    private static final long serialVersionUID = 1L;

    private MVerticalLayout bodyLayout = new MVerticalLayout();

    public BillingContainer() {
        bodyLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        setContent(bodyLayout);
    }

    void display() {
        bodyLayout.removeAllComponents();

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        try {
            LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
            if (licenseInfo.isExpired()) {
                bodyLayout.with(ELabel.h2(UserUIContext.getMessage(LicenseI18nEnum.OPT_LICENSE_EXPIRE_DATE, UserUIContext.formatDate
                        (licenseInfo.getExpireDate()))).withStyleName(WebThemes.LABEL_OVERDUE).withWidthUndefined());
            } else if (licenseInfo.isTrial()) {
                bodyLayout.with(ELabel.h2(UserUIContext.getMessage(LicenseI18nEnum.OPT_LICENSE_EXPIRE_SOON_DATE, UserUIContext.formatDate(licenseInfo.getExpireDate())))
                        .withWidthUndefined());
            } else {
                bodyLayout.with(ELabel.h2(UserUIContext.getMessage(LicenseI18nEnum.OPT_LICENSE_VALID_TO_DATE, UserUIContext.formatDate(licenseInfo.getExpireDate())))
                        .withWidthUndefined());
            }
            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 4);
            layoutHelper.addComponent(new Label(licenseInfo.getLicenseOrg()), UserUIContext.getMessage
                    (LicenseI18nEnum.FORM_ORGANIZATION), 0, 0);
            layoutHelper.addComponent(new Label(UserUIContext.formatDate(licenseInfo.getIssueDate())), UserUIContext
                    .getMessage(LicenseI18nEnum.FORM_ISSUE_DATE), 0, 1);
            layoutHelper.addComponent(new Label(UserUIContext.formatDate(licenseInfo.getExpireDate())), UserUIContext
                    .getMessage(LicenseI18nEnum.FORM_EXPIRE_DATE), 0, 2);
            layoutHelper.addComponent(new Label(licenseInfo.getMaxUsers() + ""), UserUIContext.getMessage
                    (LicenseI18nEnum.FORM_MAX_USERS), 0, 3);
            layoutHelper.getLayout().setWidth("600px");
            bodyLayout.with(layoutHelper.getLayout()).withAlign(layoutHelper.getLayout(), Alignment.TOP_CENTER);

            if (licenseInfo.isTrial() || licenseInfo.isExpired() || licenseInfo.isInvalid()) {
                MButton checkoutBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.OPT_BUY_LICENSE),
                        clickEvent -> UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow())).withStyleName(WebThemes.BUTTON_ACTION);
                MButton licenseBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_ENTER_LICENSE),
                        clickEvent -> UI.getCurrent().addWindow(new LicenseActivationWindow())).withStyleName(WebThemes.BUTTON_ACTION);

                bodyLayout.with(new Image("", new ExternalResource("http://fastspring.info/dev/sb_buttons/images/bn/fs_button05.gif")),
                        new MHorizontalLayout(checkoutBtn, licenseBtn).alignAll(Alignment.MIDDLE_CENTER));
            }
        } catch (Exception e) {
            bodyLayout.with(ELabel.h2(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID)).withWidthUndefined());
        }
    }
}
