package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class AccountSettingsPresenter extends
		AbstractPresenter<AccountSettingsView> {
	private static final long serialVersionUID = 1L;

	public AccountSettingsPresenter() {
		super(AccountSettingsView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		view.loadCurrentPlan();
	}
}
