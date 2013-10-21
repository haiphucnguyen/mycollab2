package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CrmCustomViewPresenter extends CrmGenericPresenter<CrmCustomView> {
	private static final long serialVersionUID = 1L;

	public CrmCustomViewPresenter() {
		super(CrmCustomView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmSettingContainer settingContainer = (CrmSettingContainer) container;
		settingContainer.gotoSubView("Custom Layouts");

		AppContext.addFragment("crm/setting/customlayout", "Custom Layouts");

		view.display(CrmTypeConstants.ACCOUNT);
	}

}
