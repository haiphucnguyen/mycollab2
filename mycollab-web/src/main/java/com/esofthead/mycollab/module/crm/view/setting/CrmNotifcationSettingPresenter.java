package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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
		CrmSettingContainer settingContainer = (CrmSettingContainer) container;
		settingContainer.gotoSubView("Notification");

		CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
		crmToolbar.gotoItem(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TOOLBAR_CRMNOTIFICATION_HEADER));

		CrmNotificationSettingService service = ApplicationContextUtil
				.getSpringBean(CrmNotificationSettingService.class);
		CrmNotificationSetting setting = service.findNotification(
				AppContext.getUsername(), AppContext.getAccountId());
		view.showNotificationSettings(setting);

		AppContext.addFragment("crm/setting/notification",
				"Notification Settings");
	}
}
