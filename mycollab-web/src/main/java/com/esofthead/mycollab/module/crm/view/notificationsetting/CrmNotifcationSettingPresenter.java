package com.esofthead.mycollab.module.crm.view.notificationsetting;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CrmNotifcationSettingPresenter extends
		CrmGenericPresenter<CrmNotificationSettingView> {
	private static final long serialVersionUID = 1L;

	public CrmNotifcationSettingPresenter() {
		super(CrmNotificationSettingView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
		crmToolbar.gotoItem(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TOOLBAR_CRMNOTIFICATION_HEADER));

		super.onGo(container, data);
		view.showNotificationSettings(null);
		AppContext.addFragment("crm/setting", "Customer: Settings");
	}
}
