package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.MobileApplication;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class CrmDashboardPresenter extends
		CrmGenericPresenter<CrmDashboardView> {

	private static final long serialVersionUID = -2422488836026839744L;

	public CrmDashboardPresenter() {
		super(CrmDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
		if (data == null) {
			super.onGo(navigator, data);
			EventBusFactory.getInstance().post(
					new AccountEvent.GotoList(navigator, data));
		} else {
			String[] params = (String[]) data.getParams();
			if (params == null || params.length == 0) {
				super.onGo(navigator, data);
				EventBusFactory.getInstance().post(
						new AccountEvent.GotoList(navigator, data));
			} else {
				MobileApplication.rootUrlResolver.getSubResolver("crm").handle(
						params);
			}
		}

		AppContext.getInstance().updateLastModuleVisit(ModuleNameConstants.CRM);
	}

}
