package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.crm.data.CustomViewScreenData;
import com.esofthead.mycollab.module.crm.data.NotificationSettingScreenData;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmModule;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
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
		AbstractPresenter presenter = null;
		CrmModule crmModule = (CrmModule) container;
		crmModule.addView(view);

		if (ClassUtils.instanceOf(data,
				NotificationSettingScreenData.Read.class)) {
			presenter = PresenterResolver
					.getPresenter(CrmNotifcationSettingPresenter.class);
		} else if (ClassUtils.instanceOf(data, CustomViewScreenData.Read.class)) {
			presenter = PresenterResolver
					.getPresenter(CrmCustomViewPresenter.class);
		} else {
			throw new MyCollabException("Do not support screen data " + data);
		}

		presenter.go(view, data);
	}
}
