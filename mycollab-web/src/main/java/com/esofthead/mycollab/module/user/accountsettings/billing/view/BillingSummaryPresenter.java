package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class BillingSummaryPresenter extends
		AbstractPresenter<BillingSummaryView> {
	private static final long serialVersionUID = 1L;

	public BillingSummaryPresenter() {
		super(BillingSummaryView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
			BillingContainer accountContainer = (BillingContainer) container;
			accountContainer.removeAllComponents();
			accountContainer.addComponent(view.getWidget());

			view.loadCurrentPlan();
			AccountSettingBreadcrumb breadcrumb = ViewManager
					.getView(AccountSettingBreadcrumb.class);
			breadcrumb.gotoBillingPage();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
		
	}
}
