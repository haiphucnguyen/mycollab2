package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class CrmSettingPresenter extends
		CrmGenericPresenter<CrmSettingContainer> {
	private static final long serialVersionUID = 1L;

	public CrmSettingPresenter() {
		super(CrmSettingContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {

	}
}
