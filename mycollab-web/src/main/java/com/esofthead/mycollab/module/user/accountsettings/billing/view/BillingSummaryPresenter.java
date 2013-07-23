package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class BillingSummaryPresenter extends
		AbstractPresenter<BillingSummaryView> {
	private static final long serialVersionUID = 1L;

	public BillingSummaryPresenter() {
		super(BillingSummaryView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AccountModule accountContainer = (AccountModule) container;

		accountContainer.gotoSubView("Billing");
		view.loadCurrentPlan();
		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getView(AccountSettingBreadcrumb.class);
		breadcrumb.gotoBillingPage();
	}
}
