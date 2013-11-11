package com.esofthead.mycollab.community.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class BillingPresenter extends AbstractPresenter<IBillingContainer>
		implements IBillingPresenter {
	private static final long serialVersionUID = 1L;

	public BillingPresenter() {
		super(IBillingContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AccountModule accountContainer = (AccountModule) container;

		accountContainer.gotoSubView("Billing");
	}

}
