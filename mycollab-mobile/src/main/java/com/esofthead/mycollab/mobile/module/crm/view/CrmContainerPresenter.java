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
public class CrmContainerPresenter extends
		CrmGenericPresenter<CrmContainerView> {

	private static final long serialVersionUID = -2422488836026839744L;

	public CrmContainerPresenter() {
		super(CrmContainerView.class);
	}

	@Override
	protected void onGo(ComponentContainer navigator, ScreenData<?> data) {

		super.onGo(navigator, data);
		EventBusFactory.getInstance().post(
				new AccountEvent.GotoList(navigator, data));
		String url = MobileApplication.getInstance().getInitialUrl();
		if (url != null && !url.equals("")) {
			if (url.startsWith("/")) {
				url = url.substring(1);
			}
			MobileApplication.rootUrlResolver.navigateByFragement(url);
		}

		AppContext.getInstance().updateLastModuleVisit(ModuleNameConstants.CRM);
	}

}
