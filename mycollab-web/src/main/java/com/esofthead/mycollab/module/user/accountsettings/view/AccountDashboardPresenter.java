package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class AccountDashboardPresenter extends
		AbstractPresenter<AccountDashboardView> {
	private static final long serialVersionUID = 1L;

	public AccountDashboardPresenter() {
		super(AccountDashboardView.class);
	}
	
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MainView mainView = (MainView) container;
		mainView.addView(view);
		AppContext.addFragment("account", "Account Page");
	}
}
