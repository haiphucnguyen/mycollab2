package com.esofthead.mycollab.module.user.accountsettings;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingViewImpl;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationViewImpl;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AccountContainer extends VerticalLayout implements View{

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();
	
	private Component userInformationTab;
	private Component accountSettingTab;

	public AccountContainer() {
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

		
		userInformationTab = constructUserInformationComponent();
		accountTab.addTab(userInformationTab,
				"User Information");
		
		accountSettingTab = constructAccountSettingsComponent();
		accountTab.addTab(accountSettingTab,
				"Account Settings");
		
		this.addComponent(root);
	}
	
	public void tabChange(Component component) {
		
	}

	private Component constructUserInformationComponent() {
		return ViewManager.getView(UserInformationViewImpl.class);
	}

	private com.vaadin.ui.Component constructAccountSettingsComponent() {
		return ViewManager.getView(AccountSettingViewImpl.class);
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

}
