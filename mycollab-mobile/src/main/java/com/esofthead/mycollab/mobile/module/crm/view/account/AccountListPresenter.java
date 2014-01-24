package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;

public class AccountListPresenter extends AbstractPresenter<AccountListView> {
	private static final long serialVersionUID = -3014478937143932048L;

	public AccountListPresenter() {
		super(AccountListView.class);
	}

	@Override
	protected void onGo(MobileNavigationManager navigationManager, ScreenData<?> data) {
		navigationManager.navigateTo(view.getWidget());
	}

}
