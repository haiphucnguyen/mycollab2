package com.mycollab.ondemand.shell.view;

import com.google.common.base.MoreObjects;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.ui.components.notification.RequestUploadAvatarNotification;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.domain.SimpleBillingAccount;
import com.mycollab.module.user.ui.SettingAssetsManager;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.shell.events.ShellEvent;
import com.mycollab.shell.view.AbstractMainView;
import com.mycollab.shell.view.components.AbstractAboutWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.mycollab.vaadin.web.ui.NotificationComponent;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.vaadin.hene.popupbutton.PopupButton;
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

        BillingSubscriptionMapperExt billingSubscriptionMapperExt = AppContextUtil.getSpringBean(BillingSubscriptionMapperExt.class);
        SimpleBillingSubscription subscription = billingSubscriptionMapperExt.findSubscription(AppContext.getAccountId());
        if (subscription != null) {
            MyCollabSession.putCurrentUIVariable("subscription", subscription);
            if (!subscription.canAccess()) {
                TrialBlock trialBlock = new TrialBlock();
                accountLayout.with(trialBlock).withAlign(trialBlock, Alignment.MIDDLE_LEFT);
                trialBlock.setText(String.format("<div class='informBlock'>%s<br></div>",
                        AppContext.getMessage(BillingI18nEnum.OPT_ACCOUNT_SUSPENDED)));
                AppContext.getInstance().setIsValidAccount(false);
            } else if (!subscription.isValid()) {
                TrialBlock trialBlock = new TrialBlock();
                accountLayout.with(trialBlock).withAlign(trialBlock, Alignment.MIDDLE_LEFT);
                trialBlock.setText(String.format("<div class='informBlock'>%s<br></div>",
                        AppContext.getMessage(BillingI18nEnum.OPT_PAYMENT_CHARGE_FAILED)));
                AppContext.getInstance().setIsValidAccount(true);
            }
        } else {
            SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
            TrialBlock trialBlock = new TrialBlock();
            accountLayout.with(trialBlock).withAlign(trialBlock, Alignment.MIDDLE_LEFT);

            DateTime trialFrom = new DateTime(MoreObjects.firstNonNull(billingAccount.getTrialfrom(), billingAccount.getCreatedtime()));
            DateTime trialTo = new DateTime(MoreObjects.firstNonNull(billingAccount.getTrialto(), trialFrom.plusDays(30)));
            Duration dur = new Duration(new DateTime(), trialTo);
            int daysLeft = dur.toStandardDays().getDays();
            if (daysLeft < 0) {
                trialBlock.setText("<div class='informBlock'>Trial<br></div>");
                AppContext.getInstance().setIsValidAccount(false);
            } else {
                trialBlock.setText(String.format("<div class='informBlock'>Trial ending<br>%d days " +
                        "left</div><div class='informBlock'>&gt;&gt;</div>", daysLeft));
            }
        }

        Label accountNameLabel = new Label(AppContext.getSubDomain());
        accountNameLabel.addStyleName("subDomain");
        accountLayout.addComponent(accountNameLabel);

        NotificationComponent notificationComponent = new NotificationComponent();
        accountLayout.addComponent(notificationComponent);

        if (StringUtils.isBlank(AppContext.getUser().getAvatarid())) {
            EventBusFactory.getInstance().post(new ShellEvent.NewNotification(this, new RequestUploadAvatarNotification()));
        }

        Resource userAvatarRes = UserAvatarControlFactory.createAvatarResource(AppContext.getUserAvatarId(), 24);
        final PopupButton accountMenu = new PopupButton("");
        accountMenu.setIcon(userAvatarRes);
        accountMenu.setDescription(AppContext.getUserDisplayName());

        OptionPopupContent accountPopupContent = new OptionPopupContent();

        MButton myProfileBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_PROFILE), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"preview"}));
        }).withIcon(SettingAssetsManager.getAsset(SettingUIConstants.PROFILE));
        accountPopupContent.addOption(myProfileBtn);

        MButton userMgtBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_USERS_AND_ROLES), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"user", "list"}));
        }).withIcon(SettingAssetsManager.getAsset(SettingUIConstants.USERS));
        accountPopupContent.addOption(userMgtBtn);

        MButton generalSettingBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_SETTING), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"setting", "general"}));
        }).withIcon(SettingAssetsManager.getAsset(SettingUIConstants.GENERAL_SETTING));
        accountPopupContent.addOption(generalSettingBtn);

        MButton themeCustomizeBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_THEME), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"setting", "theme"}));
        }).withIcon(SettingAssetsManager.getAsset(SettingUIConstants.THEME_CUSTOMIZE));
        accountPopupContent.addOption(themeCustomizeBtn);

        accountPopupContent.addSeparator();

        MButton helpBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_HELP)).withIcon(FontAwesome.MORTAR_BOARD);
        ExternalResource helpRes = new ExternalResource("https://community.mycollab.com/meet-mycollab/");
        BrowserWindowOpener helpOpener = new BrowserWindowOpener(helpRes);
        helpOpener.extend(helpBtn);
        accountPopupContent.addOption(helpBtn);

        MButton supportBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SUPPORT)).withIcon(FontAwesome.LIFE_SAVER);
        ExternalResource supportRes = new ExternalResource("http://support.mycollab.com/");
        BrowserWindowOpener supportOpener = new BrowserWindowOpener(supportRes);
        supportOpener.extend(supportBtn);
        accountPopupContent.addOption(supportBtn);

        MButton translateBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_TRANSLATE)).withIcon(FontAwesome.PENCIL);
        ExternalResource translateRes = new ExternalResource("https://community.mycollab.com/docs/developing-mycollab/translating/");
        BrowserWindowOpener translateOpener = new BrowserWindowOpener(translateRes);
        translateOpener.extend(translateBtn);
        accountPopupContent.addOption(translateBtn);

        MButton myAccountBtn = new MButton(AppContext.getMessage(AdminI18nEnum.VIEW_BILLING), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"billing"}));
        }).withIcon(SettingAssetsManager.getAsset(SettingUIConstants.BILLING));
        accountPopupContent.addOption(myAccountBtn);

        accountPopupContent.addSeparator();
        MButton aboutBtn = new MButton("About MyCollab", clickEvent -> {
            accountMenu.setPopupVisible(false);
            Window aboutWindow = ViewManager.getCacheComponent(AbstractAboutWindow.class);
            UI.getCurrent().addWindow(aboutWindow);
        }).withIcon(FontAwesome.INFO_CIRCLE);
        accountPopupContent.addOption(aboutBtn);

        Button releaseNotesBtn = new Button("Release Notes");
        ExternalResource releaseNotesRes = new ExternalResource("https://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/releases/");
        BrowserWindowOpener releaseNotesOpener = new BrowserWindowOpener(releaseNotesRes);
        releaseNotesOpener.extend(releaseNotesBtn);

        releaseNotesBtn.setIcon(FontAwesome.BULLHORN);
        accountPopupContent.addOption(releaseNotesBtn);

        MButton signoutBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SIGNOUT), clickEvent -> {
            accountMenu.setPopupVisible(false);
            EventBusFactory.getInstance().post(new ShellEvent.LogOut(this, null));
        }).withIcon(FontAwesome.SIGN_OUT);
        accountPopupContent.addSeparator();
        accountPopupContent.addOption(signoutBtn);

        accountMenu.setContent(accountPopupContent);
        accountLayout.addComponent(accountMenu);
        return accountLayout;
    }

    private static class TrialBlock extends MHorizontalLayout {
        private ELabel informLbl;

        TrialBlock() {
            informLbl = ELabel.html("").withStyleName("trialEndingNotification");
            informLbl.setHeight("100%");
            with(informLbl).withStyleName("trialInformBox")
                    .withMargin(new MarginInfo(false, true, false, false)).withFullHeight();
            this.addLayoutClickListener(layoutClickEvent -> EventBusFactory.getInstance().post(
                    new ShellEvent.GotoUserAccountModule(this, new String[]{"billing"})));
        }

        void setText(String value) {
            informLbl.setValue(value);
        }
    }
}
