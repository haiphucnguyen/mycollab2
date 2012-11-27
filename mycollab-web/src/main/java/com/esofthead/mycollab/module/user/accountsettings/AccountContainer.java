package com.esofthead.mycollab.module.user.accountsettings;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingViewImpl;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationView;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationViewImpl;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.DefaultLazyComponent;
import com.esofthead.mycollab.vaadin.ui.ILazyComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AccountContainer extends VerticalLayout implements View {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();

	private ILazyComponent<UserInformationViewImpl> userInformationTab;
	private ILazyComponent<AccountSettingViewImpl> accountSettingTab;

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
		
		UserInformationView userView = ViewManager.getView(UserInformationViewImpl.class);
		UserInformationPresenter userInformationPresenter = new UserInformationPresenter(userView);
		
		accountTab.addTab((Component)userView, "User Information");

		accountSettingTab = new DefaultLazyComponent<AccountSettingViewImpl>(AccountSettingViewImpl.class);
		
		
		accountTab.addTab(accountSettingTab, "Account Settings");

		this.addComponent(root);
	}

	public void tabChange(Component component) {

	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

}
