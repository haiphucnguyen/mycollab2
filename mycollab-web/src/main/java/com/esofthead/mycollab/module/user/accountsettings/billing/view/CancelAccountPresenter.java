package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.ComponentContainer;

public class CancelAccountPresenter extends
		AbstractPresenter<CancelAccountView> {
	private static final long serialVersionUID = 1L;

	public CancelAccountPresenter() {
		super(CancelAccountView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		BillingContainer accountContainer = (BillingContainer) container;
		accountContainer.removeAllComponents();
		accountContainer.addComponent(view.getWidget());

		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getView(AccountSettingBreadcrumb.class);
		breadcrumb.gotoCancelAccountPage();
	}

}
