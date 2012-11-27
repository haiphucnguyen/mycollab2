package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.module.user.ui.accountsettings.AccountSettingViewImpl;
import com.esofthead.mycollab.module.user.ui.accountsettings.UserInformationViewImpl;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AccountViewImpl extends AbstractView implements AccountView {

	private final HorizontalLayout root;
	private final DetachedTabs accountTab;
	private final CssLayout accountSpace = new CssLayout();

	public AccountViewImpl() {
		this.setStyleName("accountViewContainer");
		// this.setWidth("1130px");
		this.setMargin(false);
		root = new HorizontalLayout();
		// root.setWidth("1000px");
		// root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		// root.setLocked(true);
		// root.setSizeFull();

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

	@Override
	public void attach() {
		super.attach();
	}

	private void buildComponents() {
		accountTab.addTab(constructUserInformationComponent(),
				"User Information");
		accountTab.addTab(constructAccountSettingsComponent(),
				"Account Settings");
	}

	// private void addView(AbstractView view) {
	// this.removeAllComponents();
	// this.addComponent(view);
	// }

	private com.vaadin.ui.Component constructUserInformationComponent() {
		return ViewManager.getView(UserInformationViewImpl.class);
	}

	private com.vaadin.ui.Component constructAccountSettingsComponent() {
		return ViewManager.getView(AccountSettingViewImpl.class);
	}

}
