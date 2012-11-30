package com.esofthead.mycollab.module.user.accountsettings;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingsPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingsViewImpl;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationViewImpl;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AccountDashboardViewImpl extends AbstractView implements
		AccountDashboardView {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();

	private UserInformationPresenter userInformationPresenter;
	private AccountSettingsPresenter accountSettingsPresenter;

	public AccountDashboardViewImpl() {
		this.setStyleName("accountViewContainer");
		this.setMargin(false);
		root = new HorizontalLayout();

		accountSpace.setSizeFull();
		accountTab = new DetachedTabs.Vertical(accountSpace);
		accountTab.setWidth("200px");
		accountTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setSizeFull();
		menu.setStyleName("sidebar-menu");

		menu.addComponent(accountTab);
		root.addComponent(menu);
		root.addComponent(accountSpace);

		buildComponents();

		this.addComponent(root);
	}

	private void buildComponents() {
		accountTab.addTab(constructUserInformationComponent(),
				"User Information");
		accountTab.addTab(constructAccountSettingsComponent(),
				"Account Settings");

		accountTab.addTabChangedListener(new DetachedTabs.TabChangedListener() {

			@Override
			public void tabChanged(TabChangedEvent event) {
				Button btn = event.getSource();
				String caption = btn.getCaption();
				if ("User Information".equals(caption)) {
					gotoUserInformation();
				} else if ("Account Settings".equals(caption)) {
					gotoAccountSettings();
				}
			}
		});
	}

	private ComponentContainer constructAccountSettingsComponent() {
		AccountSettingsViewImpl view = ViewManager
				.getView(AccountSettingsViewImpl.class);
		accountSettingsPresenter = new AccountSettingsPresenter(view);
		return view;
	}

	private ComponentContainer constructUserInformationComponent() {
		UserInformationViewImpl view = ViewManager
				.getView(UserInformationViewImpl.class);
		userInformationPresenter = new UserInformationPresenter(view);
		return view;
	}

	@Override
	public void gotoUserInformation() {
		accountTab.selectTab("User Information");
	}

	@Override
	public void gotoAccountSettings() {
		accountTab.selectTab("Account Settings");
	}

}
