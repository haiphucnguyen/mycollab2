/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingPresenter;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfilePresenter;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserPermissionManagementPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.BillingScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class AccountModuleImpl extends AbstractView implements AccountModule {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();

	private ProfilePresenter profilePresenter;
	private UserPermissionManagementPresenter userPermissionPresenter;
	private IBillingPresenter billingPresenter;

	private final AccountSettingBreadcrumb breadcrumb;

	public AccountModuleImpl() {
		ControllerRegistry.addController(new UserAccountController(this));
		this.setWidth("100%");
		this.setMargin(false);

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setWidth("100%");
		contentWrapper.setStyleName("accountViewContainer");
		contentWrapper.addStyleName("main-content-wrapper");
		this.addComponent(contentWrapper);

		final HorizontalLayout headerWrapper = new HorizontalLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.setMargin(true);
		this.breadcrumb = ViewManager.getView(AccountSettingBreadcrumb.class);
		headerWrapper.addComponent(this.breadcrumb);
		contentWrapper.addComponent(headerWrapper);

		this.root = new HorizontalLayout();
		this.root.setStyleName("menuContent");
		this.root.setWidth("100%");

		this.accountSpace.setWidth("100%");
		this.accountSpace.setStyleName("account-dashboard");
		this.accountTab = new DetachedTabs.Vertical(this.accountSpace);
		this.accountTab.setSizeFull();
		this.accountTab.setHeight(null);

		final CssLayout menu = new CssLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");

		menu.addComponent(this.accountTab);
		this.root.addComponent(menu);
		this.root.addComponent(this.accountSpace);
		this.root.setExpandRatio(this.accountSpace, 1.0f);

		this.buildComponents();

		contentWrapper.addComponent(this.root);
		this.addComponent(contentWrapper);
	}

	private void buildComponents() {
		this.accountTab.addTab(this.constructUserInformationComponent(),
				new MenuButton("User Information", "menu_profile.png"));
		this.accountTab.addTab(this.constructAccountSettingsComponent(),
				new MenuButton("Billing", "menu_account.png"));
		this.accountTab.addTab(this.constructUserPermissionComponent(),
				new MenuButton("Users & Permissions", "menu_team.png"));

		this.accountTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(final TabChangedEvent event) {
						final Button btn = event.getSource();
						final String caption = btn.getCaption();
						if ("User Information".equals(caption)) {
							AccountModuleImpl.this.profilePresenter.go(
									AccountModuleImpl.this, null);
						} else if ("Billing".equals(caption)) {
							billingPresenter.go(AccountModuleImpl.this,
									new BillingScreenData.BillingSummary());
						} else if ("Users & Permissions".equals(caption)) {
							AccountModuleImpl.this.userPermissionPresenter.go(
									AccountModuleImpl.this, null);
						}
					}
				});
	}

	private ComponentContainer constructAccountSettingsComponent() {
		this.billingPresenter = PresenterResolver
				.getPresenter(IBillingPresenter.class);
		return this.billingPresenter.getView();
	}

	private ComponentContainer constructUserInformationComponent() {
		this.profilePresenter = PresenterResolver
				.getPresenter(ProfilePresenter.class);
		return this.profilePresenter.getView();
	}

	private ComponentContainer constructUserPermissionComponent() {
		this.userPermissionPresenter = PresenterResolver
				.getPresenter(UserPermissionManagementPresenter.class);
		return this.userPermissionPresenter.getView();
	}

	@Override
	public void gotoSubView(final String viewName) {
		this.accountTab.selectTab(viewName);
	}

	private static class MenuButton extends Button {
		public MenuButton(final String caption, final String iconResource) {
			super(caption);
			this.setIcon(MyCollabResource.newResource("icons/22/user/"
					+ iconResource));
			this.setStyleName("link");
		}
	}

	@Override
	public void gotoUserProfilePage() {
		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(this, null));
	}
}
