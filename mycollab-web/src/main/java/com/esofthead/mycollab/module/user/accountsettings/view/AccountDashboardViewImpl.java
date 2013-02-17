package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class AccountDashboardViewImpl extends AbstractView implements
		AccountDashboardView {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();
	private final AccountController controller = new AccountController(this);

	private UserInformationPresenter userInformationPresenter;
	private UserPermissionManagementPresenter userPermissionPresenter;
	private AccountSettingsPresenter accountSettingPresenter;

	public AccountDashboardViewImpl() {
		this.setStyleName("accountViewContainer");
		this.setMargin(false);
		root = new HorizontalLayout();
		root.setStyleName("menuContent");
		root.setWidth("100%");

		accountSpace.setWidth("100%");
		accountSpace.setStyleName("account-dashboard");
		accountTab = new DetachedTabs.Vertical(accountSpace);
		accountTab.setSizeFull();
		accountTab.setHeight(null);

		CssLayout menu = new CssLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");

		menu.addComponent(accountTab);
		root.addComponent(menu);
		root.addComponent(accountSpace);
		root.setExpandRatio(accountSpace, 1.0f);

		buildComponents();

		this.addComponent(root);
	}

	private void buildComponents() {
		accountTab.addTab(constructUserInformationComponent(),
				"User Information");
		accountTab.addTab(constructAccountSettingsComponent(),
				"Account Settings");

		if (AppContext.canRead(RolePermissionCollections.USER_USER)
				|| AppContext.canRead(RolePermissionCollections.USER_ROLE)) {
			accountTab.addTab(constructUserPermissionComponent(),
					"Users & Permissions");
		}

		accountTab.addTabChangedListener(new DetachedTabs.TabChangedListener() {
			@Override
			public void tabChanged(TabChangedEvent event) {
				Button btn = event.getSource();
				String caption = btn.getCaption();
				if ("User Information".equals(caption)) {
					gotoUserInformation();
				} else if ("Account Settings".equals(caption)) {
					gotoAccountSettings();
				} else if ("Users & Permissions".equals(caption)) {
					UserPermissionManagementPresenter presenter = PresenterResolver
							.getPresenter(UserPermissionManagementPresenter.class);
					presenter.go(AccountDashboardViewImpl.this, null);
				}
			}
		});
	}

	private ComponentContainer constructAccountSettingsComponent() {
		accountSettingPresenter = PresenterResolver
				.getPresenter(AccountSettingsPresenter.class);
		return accountSettingPresenter.getView();
	}

	private ComponentContainer constructUserInformationComponent() {
		userInformationPresenter = PresenterResolver
				.getPresenter(UserInformationPresenter.class);
		return userInformationPresenter.getView();
	}

	private ComponentContainer constructUserPermissionComponent() {
		userPermissionPresenter = PresenterResolver
				.getPresenter(UserPermissionManagementPresenter.class);
		return userPermissionPresenter.getView();
	}

	@Override
	public void gotoUserInformation() {
		userInformationPresenter.go(AccountDashboardViewImpl.this, null);
	}

	@Override
	public void gotoAccountSettings() {
		accountTab.selectTab("Account Settings");
	}
}
