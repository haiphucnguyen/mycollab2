package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CrmHomePresenter extends CrmGenericPresenter<CrmHomeView> {

	private static final long serialVersionUID = 1L;

	public CrmHomePresenter() {
		super(CrmHomeView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);

		view.displayDashboard();
		AppContext.addFragment("crm/dashboard", "Customer Dashboard");
	}

}
