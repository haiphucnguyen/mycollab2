package com.mycollab.premium.shell.view;

import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.common.ui.components.notification.RequestUploadAvatarNotification;
import com.mycollab.common.ui.components.notification.SmtpSetupNotification;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.license.LicenseInfo;
import com.mycollab.license.service.LicenseResolver;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.premium.shell.view.components.BuyPremiumSoftwareWindow;
import com.mycollab.premium.shell.view.components.LicenseActivationWindow;
import com.mycollab.pro.vaadin.web.ui.NotificationComponent;
import com.mycollab.shell.event.ShellEvent;
import com.mycollab.shell.view.AbstractMainView;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@ViewComponent
public class MainViewImpl extends AbstractMainView {

    @Override
    protected MHorizontalLayout buildAccountMenuLayout() {
        accountLayout.removeAllComponents();

        Label accountNameLabel = new Label(AppUI.getSubDomain());
        accountNameLabel.addStyleName("subDomain");
        accountLayout.addComponent(accountNameLabel);

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();

        if (licenseInfo.isExpired()) {
            if (licenseInfo.isTrial()) {
                UI.getCurrent().addWindow(new LicenseActivationWindow());
                UserUIContext.getInstance().setIsValidAccount(false);
            } else if (licenseInfo.isInvalid()) {
                MButton buyPremiumBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.EXPIRE_NOTIFICATION),
                        clickEvent -> UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow()))
                        .withIcon(VaadinIcons.CART_O).withStyleName("ad");
                accountLayout.addComponent(buyPremiumBtn);
                UserUIContext.getInstance().setIsValidAccount(false);
            } else {
                MButton buyPremiumBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.EXPIRE_NOTIFICATION),
                        clickEvent -> UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow()))
                        .withIcon(VaadinIcons.CART_O).withStyleName("ad");
                accountLayout.addComponent(buyPremiumBtn);
            }
        } else if (licenseInfo.isTrial()) {
            // TODO
//            Duration dur = new Duration(new DateTime(), new DateTime(licenseInfo.getExpireDate()));
//            int days = dur.toStandardDays().getDays();
//            MButton buyPremiumBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.TRIAL_NOTIFICATION, days),
//                    clickEvent -> UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow()))
//                    .withIcon(VaadinIcons.CART_O).withStyleName("ad");
//            accountLayout.addComponent(buyPremiumBtn);
        }

        NotificationComponent notificationComponent = new NotificationComponent();
        accountLayout.addComponent(notificationComponent);

        if (StringUtils.isBlank(UserUIContext.getUser().getAvatarid())) {
            EventBusFactory.getInstance().post(new ShellEvent.NewNotification(this, new RequestUploadAvatarNotification()));
        }

        ExtMailService mailService = AppContextUtil.getSpringBean(ExtMailService.class);
        if (!mailService.isMailSetupValid()) {
            EventBusFactory.getInstance().post(new ShellEvent.NewNotification(this, new SmtpSetupNotification()));
        }

        return accountLayout;
    }
}
