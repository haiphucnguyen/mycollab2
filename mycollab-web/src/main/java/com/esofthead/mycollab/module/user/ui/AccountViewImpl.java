package com.esofthead.mycollab.module.user.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.user.ui.accountsettings.AccountSettingViewImpl;
import com.esofthead.mycollab.module.user.ui.accountsettings.UserInformationViewImpl;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class AccountViewImpl extends AbstractView implements AccountView {

	private HorizontalSplitPanel root;
	private DetachedTabs accountTab;
	private CssLayout accountSpace = new CssLayout();

	@Override
	protected void initializeLayout() {
		root = new HorizontalSplitPanel();
		root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		root.setLocked(true);
		root.setSizeFull();

		accountSpace.setSizeFull();
		accountTab = new DetachedTabs.Vertical(accountSpace);
		accountTab.setSizeFull();
		accountTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setSizeFull();
		menu.setStyleName("sidebar-menu");

		menu.addComponent(accountTab);
		root.setFirstComponent(menu);
		root.setSecondComponent(accountSpace);

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

	private com.vaadin.ui.Component constructUserInformationComponent() {
		return AppContext.getView(UserInformationViewImpl.class);
	}

	private com.vaadin.ui.Component constructAccountSettingsComponent() {
		return AppContext.getView(AccountSettingViewImpl.class);
	}

}
