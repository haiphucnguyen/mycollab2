package com.esofthead.mycollab.module.user.accountsettings;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingsPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.UserInformationPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent 
public class AccountDashboardViewImpl extends AbstractView implements
		AccountDashboardView {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();

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
		AccountSettingsPresenter presenter = PresenterResolver
				.getPresenter(AccountSettingsPresenter.class);
		return presenter.getView();
	}

	private ComponentContainer constructUserInformationComponent() {
		UserInformationPresenter presenter = PresenterResolver
				.getPresenter(UserInformationPresenter.class);
		return presenter.getView();
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
