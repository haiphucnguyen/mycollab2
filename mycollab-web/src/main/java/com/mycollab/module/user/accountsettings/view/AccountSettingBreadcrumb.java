package com.mycollab.module.user.accountsettings.view;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.mycollab.module.user.accountsettings.localization.RoleI18nEnum;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.module.user.accountsettings.view.event.AccountBillingEvent;
import com.mycollab.module.user.accountsettings.view.event.ProfileEvent;
import com.mycollab.module.user.domain.Role;
import com.mycollab.module.user.domain.SimpleRole;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.event.RoleEvent;
import com.mycollab.module.user.event.UserEvent;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.CommonUIFactory;
import com.mycollab.vaadin.web.ui.utils.LabelStringGenerator;
import com.vaadin.breadcrumb.Breadcrumb;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class AccountSettingBreadcrumb extends Breadcrumb implements CacheableComponent {
    private static final long serialVersionUID = 1L;

    private static LabelStringGenerator menuLinkGenerator = new BreadcrumbLabelStringGenerator();

    public AccountSettingBreadcrumb() {
        this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setUseDefaultClickBehaviour(false);
        this.addLink(new Button(null, clickEvent -> EventBusFactory.getInstance().post(new ProfileEvent.GotoProfileView(this))));
    }

    public void gotoProfile() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(UserI18nEnum.OPT_PROFILE)));
        AppUI.addFragment("account/preview", UserUIContext.getMessage(UserI18nEnum.OPT_PROFILE));
    }

    public void gotoSetup() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_SETUP)));
        AppUI.addFragment("account/setup", UserUIContext.getMessage(AdminI18nEnum.VIEW_SETUP));
    }

    public void gotoBillingPage() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING)));
        AppUI.addFragment("account/billing", UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING));
    }

    public void gotoBillingHistory() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING),
                clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.GotoSummary(this, null))));
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING_HISTORY)));
        AppUI.addFragment("account/billing/history", UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING_HISTORY));
    }

    public void gotoCancelAccountPage() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_BILLING),
                clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.GotoSummary(this, null))));
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.ACTION_CANCEL_ACCOUNT)));
        AppUI.addFragment("account/billing/cancel", UserUIContext.getMessage(AdminI18nEnum.ACTION_CANCEL_ACCOUNT));
    }

    public void gotoUserList() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(UserI18nEnum.LIST)));
        AppUI.addFragment("account/user/list", UserUIContext.getMessage(UserI18nEnum.LIST));
    }

    public void gotoUserRead(SimpleUser user) {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(UserI18nEnum.LIST), new GotoUserListListener()));
        this.addLink(generateBreadcrumbLink(user.getDisplayName()));

        AppUI.addFragment("account/user/preview/" + UrlEncodeDecoder.encode(user.getUsername()),
                UserUIContext.getMessage(GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE, UserUIContext.getMessage(UserI18nEnum.SINGLE), user.getDisplayName()));
    }

    public void gotoUserAdd() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(UserI18nEnum.LIST), new GotoUserListListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADD)));
        AppUI.addFragment("account/user/add", UserUIContext.getMessage(AdminI18nEnum.ACTION_INVITE_NEW_USER));
    }

    public void gotoUserEdit(final SimpleUser user) {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(UserI18nEnum.LIST), new GotoUserListListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(generateBreadcrumbLink(user.getDisplayName(),
                clickEvent -> EventBusFactory.getInstance().post(new UserEvent.GotoRead(this, user.getUsername()))));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT)));
        AppUI.addFragment("account/user/edit/" + UrlEncodeDecoder.encode(user.getUsername()),
                UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, UserUIContext.getMessage(UserI18nEnum.SINGLE), user.getDisplayName()));
    }

    public void gotoGeneralSetting() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_SETTING)));
        AppUI.addFragment("account/setting/general", UserUIContext.getMessage(AdminI18nEnum.VIEW_SETTING));
    }

    public void gotoMakeTheme() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(AdminI18nEnum.VIEW_SETTING)));
        AppUI.addFragment("account/setting/theme", UserUIContext.getMessage(AdminI18nEnum.VIEW_SETTING));
    }

    private static class GotoUserListListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            EventBusFactory.getInstance().post(new UserEvent.GotoList(this, null));
        }
    }

    public void gotoRoleList() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(RoleI18nEnum.LIST)));
        AppUI.addFragment("account/role/list", UserUIContext.getMessage(RoleI18nEnum.LIST));
    }

    public void gotoRoleAdd() {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(RoleI18nEnum.LIST), new GotoRoleListListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADD)));
        AppUI.addFragment("account/role/add", UserUIContext.getMessage(RoleI18nEnum.NEW));
    }

    public void gotoRoleRead(SimpleRole role) {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(RoleI18nEnum.LIST), new GotoRoleListListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(generateBreadcrumbLink(role.getRolename()));
        AppUI.addFragment("account/role/preview/" + UrlEncodeDecoder.encode(role.getId()),
                UserUIContext.getMessage(GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE, UserUIContext.getMessage(RoleI18nEnum.SINGLE), role.getRolename()));
    }

    public void gotoRoleEdit(final Role role) {
        this.select(0);
        this.addLink(new Button(UserUIContext.getMessage(RoleI18nEnum.LIST), new GotoRoleListListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(generateBreadcrumbLink(role.getRolename(),
                clickEvent -> EventBusFactory.getInstance().post(new RoleEvent.GotoRead(this, role.getId()))));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT)));
        AppUI.addFragment("account/role/edit/" + UrlEncodeDecoder.encode(role.getId()),
                UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, UserUIContext.getMessage(RoleI18nEnum.SINGLE), role.getRolename()));
    }

    private static class GotoRoleListListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            EventBusFactory.getInstance().post(new RoleEvent.GotoList(this, null));
        }
    }

    private static Button generateBreadcrumbLink(String linkName) {
        return CommonUIFactory.createButtonTooltip(menuLinkGenerator.handleText(linkName), linkName);
    }

    private static Button generateBreadcrumbLink(String linkName, Button.ClickListener listener) {
        return CommonUIFactory.createButtonTooltip(menuLinkGenerator.handleText(linkName), linkName, listener);
    }

    private static class BreadcrumbLabelStringGenerator implements LabelStringGenerator {

        @Override
        public String handleText(String value) {
            if (value.length() > 35) {
                return value.substring(0, 35) + "...";
            }
            return value;
        }

    }
}
