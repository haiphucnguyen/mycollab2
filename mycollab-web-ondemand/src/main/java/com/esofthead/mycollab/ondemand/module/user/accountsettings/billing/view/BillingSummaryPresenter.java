/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_BILLING, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class BillingSummaryPresenter extends
		AbstractPresenter<BillingSummaryView> {
	private static final long serialVersionUID = 1L;

	public BillingSummaryPresenter() {
		super(BillingSummaryView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		IBillingContainer accountContainer = (IBillingContainer) container;
		accountContainer.removeAllComponents();
		accountContainer.addComponent(view.getWidget());

		view.loadCurrentPlan();
		AccountSettingBreadcrumb breadcrumb = ViewManager
				.getCacheComponent(AccountSettingBreadcrumb.class);
		breadcrumb.gotoBillingPage();

	}
}
