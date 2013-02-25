package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.AccountSettingsPresenter;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfilePresenter;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserPermissionManagementPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
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

	private ProfilePresenter profilePresenter;
	private UserPermissionManagementPresenter userPermissionPresenter;
	private AccountSettingsPresenter accountSettingPresenter;

	public AccountDashboardViewImpl() {
		ControllerRegistry.getInstance().addController(
				new UserAccountController(this));
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
					profilePresenter.go(AccountDashboardViewImpl.this, null);
				} else if ("Account Settings".equals(caption)) {

				} else if ("Users & Permissions".equals(caption)) {
					userPermissionPresenter.go(AccountDashboardViewImpl.this,
							null);
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
		profilePresenter = PresenterResolver
				.getPresenter(ProfilePresenter.class);
		return profilePresenter.getView();
	}

	private ComponentContainer constructUserPermissionComponent() {
		userPermissionPresenter = PresenterResolver
				.getPresenter(UserPermissionManagementPresenter.class);
		return userPermissionPresenter.getView();
	}

	@Override
	public void gotoSubView(String viewName) {
		accountTab.selectTab(viewName);
	}
}
