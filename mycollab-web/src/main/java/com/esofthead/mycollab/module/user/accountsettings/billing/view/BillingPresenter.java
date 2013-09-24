package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.BillingScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BillingPresenter extends AbstractPresenter<BillingContainer> {
	private static final long serialVersionUID = 1L;

	public BillingPresenter() {
		super(BillingContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AccountModule accountContainer = (AccountModule) container;

		accountContainer.gotoSubView("Billing");

		AbstractPresenter<?> presenter = null;

		if (data instanceof BillingScreenData.BillingSummary) {
			presenter = PresenterResolver
					.getPresenter(BillingSummaryPresenter.class);
		} else if (data instanceof BillingScreenData.CancelAccount) {
			presenter = PresenterResolver
					.getPresenter(CancelAccountPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data " + data);
		}

		presenter.go(view.getWidget(), data);
	}

}
