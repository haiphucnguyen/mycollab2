package com.esofthead.mycollab.community.module.crm.view.setting;

import com.esofthead.mycollab.module.crm.view.setting.CrmSettingContainer;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomViewPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CrmCustomViewPresenter extends AbstractPresenter<ICrmCustomView>
		implements ICrmCustomViewPresenter {
	private static final long serialVersionUID = 1L;

	public CrmCustomViewPresenter() {
		super(ICrmCustomView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmSettingContainer settingContainer = (CrmSettingContainer) container;
		settingContainer.gotoSubView("Custom Layouts");

		AppContext.addFragment("crm/setting/customlayout", "Custom Layouts");

	}

}
